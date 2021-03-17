'use strict';

_TRAVEL_BOT_APP.service("cityApiService", ['$http', '$q', function ($http, $q) {
    return {
        cityApi: cityApi
    };

    function cityApi(req) {
        let defer = $q.defer();
        $http(req)
            .then(
                function (success) {
                    defer.resolve(success);
                },
                function (error) {
                    defer.reject(error);
                }
            );
        return defer.promise;
    }

}]);
