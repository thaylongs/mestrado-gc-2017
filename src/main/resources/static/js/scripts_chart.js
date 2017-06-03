/**
 * Created by guilherme on 24/05/17.
 */
$(document).ready(function() {
    'use strict';
    var chartData = gerarDadosParaGrafico();
    var revisionChart = new Chart("revisionChart", {
        type: 'line',
        data: {
        labels: chartData.labels,
            datasets: [{
                label:"Erros",
                fill:false,
                borderColor: "#e57373",
                data: chartData.datasets.error
            },{
                label:"Avisos",
                fill:false,
                borderColor: "#dce775",
                data: chartData.datasets.warning
            },{
                label:"Notificações",
                fill:false,
                borderColor: "#4fc3f7",
                data: chartData.datasets.notice
            }
            ]
        },
        options: {
            responsive: true,
            elements: {
                line: {
                    tension: 0.5
                }
            }
        }
    });

    function gerarDadosParaGrafico() {
        var data = {
            "labels": [],
            "datasets": {
                "error": [],
                "warning": [],
                "notice": []
            }
        };
        $('.historico-revision-group-item').each(function(index) {
            var that = $(this);
            data.labels.push(moment(that.find('.item-hora').text()).calendar());
            data.datasets.error.push(Number(that.find('.item-erros').text()));
            data.datasets.warning.push(Number(that.find('.item-avisos').text()));
            data.datasets.notice.push(Number(that.find('.item-notifs').text()));
        });
        data.labels = data.labels.slice().reverse();
        data.datasets.error = data.datasets.error.slice().reverse();
        data.datasets.warning = data.datasets.warning.slice().reverse();
        data.datasets.notice = data.datasets.notice.slice().reverse();
        return data;
    }
});