/**
 * Created by guilherme on 24/05/17.
 */
$(document).ready(function() {
    'use strict';
    var revisionChart = new Chart("revisionChart", {
        type: 'line',
        data: [{
            x: -10,
            y: 0
        }, {
            x: 0,
            y: 10
        }, {
            x: 10,
            y: 5
        }],
        options: {
            responsive: true
        }
    });
});