'use strict';

const _TRAVEL_BOT_APP = angular.module("travelBotApp", [])
    .run(function ($rootScope) {
        $rootScope.cities = [

        ];
        $rootScope.messages = [

        ];
        $rootScope.cancelMsg = function (obj) {
            obj.message.class = "";
        }
    });
