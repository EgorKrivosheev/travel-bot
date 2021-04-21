'use strict';

_TRAVEL_BOT_APP.controller("appController", ['$scope', 'cityApiService', '$rootScope',
    function ($scope, cityApiService, $rootScope) {
        $scope.isVisibleForm = 'none';
        $scope.titleAddBtn = "Открыть форму добавления";
        $scope.cityForm = {
            "name" : "",
            "info" : ""
        };
        $scope.getCities = function getCities() {
            cityApiService.cityApi({
                method: 'GET',
                url: 'cities'
            }).then(
                function (success){
                    // If cities not equals cities of database then update
                    if (!angular.equals(success.data.cities, $rootScope.cities)) {
                        $rootScope.cities = success.data.cities;
                        $scope.addMessage(success.data.message);
                    }
                },
                function (error) {
                    if (error.data.message !== "") {
                        $scope.addMessage(error.data.message);
                    }
                }
            );
        }
        // Load
        $scope.getCities();
        setInterval($scope.getCities, 10000);
        // If main have scroll add class active-scroll(padding right less on 0.5em)
        $scope.isActiveScroll = function () {
            if (main.clientHeight < main.scrollHeight) {
                return "active-scroll";
            } else {
                return "";
            }
        }
        // Add new message
        $scope.addMessage = function (text) {
            $rootScope.messages.push({"class":"visible", "text":text});
            if ($rootScope.messages.length !== -1) {
                setTimeout(function () {
                    $rootScope.messages.splice(0, 1);
                }, 5000);
            }
        }
        $scope.openForm = function (str) {
            $scope.isVisibleForm = $scope.isVisibleForm === 'none' ? '' : 'none';
            $scope.isVisibleBtnForm = str === 'изменения';
            $scope.titleAddBtn = $scope.isVisibleForm ?
                'Открыть форму ' + str : 'Закрыть форму ' + str;
            $scope.classOpenForm = $scope.isVisibleForm ?
                '' : 'open-form';
            $scope.addMessage($scope.isVisibleForm ?
                'Закрыта форма для '+ str +'!' : 'Открыта форма для ' + str +'!');
        }
        $scope.openAddForm = function () {
            $scope.cityForm = {
                "name" : "", "info" : ""
            }
            $scope.openForm('добавления');
        }
        $scope.openEditForm = function (city) {
            $scope.cityForm = angular.copy(city);
            $scope.openForm('изменения');
        }
        $scope.addCity = function () {
            let req = {
                method: 'POST',
                url: 'city/add',
                params: { name: $scope.cityForm.name, info: $scope.cityForm.info }
            }
            $scope.cityApi(req);
            $scope.cityForm = { "name" : "", "info" : "" }
        }
        $scope.editCity = function () {
            let req = {
                method: 'PUT',
                url: 'city/edit',
                params: { id: $scope.cityForm.id, name: $scope.cityForm.name, info: $scope.cityForm.info }
            }
            $scope.cityApi(req);
        }
        $scope.deleteCity = function (id) {
            let req = {
                method: 'DELETE',
                url: 'city/delete',
                params: { id: id }
            }
            $scope.cityApi(req);
        }
        $scope.cityApi = function cityApi(req) {
            cityApiService.cityApi(req)
                .then(
                    function (success){
                        $scope.addMessage(success.data.message);
                        $scope.getCities();
                    },
                    function (error) {
                        if (error.data.message !== "") {
                            $scope.addMessage(error.data.message);
                        } else {
                            $scope.addMessage(error.data.error + " " + error.data.status + "!");
                        }
                    }
                );
        }
    }
]);
