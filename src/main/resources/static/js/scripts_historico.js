/**
 * Created by guilherme on 20/05/17.
 */
$(document).ready(function() {
    'use strict';
    CKEDITOR.config.readOnly = true;
    $(".historico-revisions").css("height", (window.innerHeight - 80) + "px");

    var revisaoAtual = $(".historico-revision-group")
                            .find(".historico-revision-group-item")
                            .first()
                            .addClass("latest");
    carregarInformacoesDeRevisao(revisaoAtual);
    applyAnimations();

    $(".historico-revision-group-item").on("click", function () {
        var revisao= $(this);
        carregarInformacoesDeRevisao(revisao);
    });

    function carregarInformacoesDeRevisao(revisao) {
        if (revisao.length >= 1) {
            var titulo = revisao.find(".item-titulo").text();
            var dataModificacao = revisao.find(".item-hora").text();
            var conteudo = revisao.find(".item-conteudo").text();
            $("#document_name_data").text(titulo + ", " + moment(dataModificacao).calendar());
            CKEDITOR.instances['ckeditor'].setData($.base64.decode(conteudo));
            selecionarRevisao(revisao);
        }
        desabilitarBotaoDeRestauracaoQuandoCarregarRevisaoAtual(revisao);
    }
    
    function desabilitarBotaoDeRestauracaoQuandoCarregarRevisaoAtual(revisao) {
        revisao.is(".latest") || revisao.length == 0? $("#btn-restore-document").addClass("disabled"):
            $("#btn-restore-document").removeClass("disabled");
    }
    
    function selecionarRevisao(revisao) {
        $(".selected").removeClass("selected");
        revisao.addClass("selected");
    }

    function applyAnimations () {
        $('#btn-restore-document').animateCss('fadeInRight');
    }

    $('#btn-restore-document').on('click', function () {
        var that = $(this);
        var versaoSelecionada = $(".selected");
        var json = {
            'name': versaoSelecionada.find(".item-titulo").text(),
            'data': versaoSelecionada.find(".item-conteudo").text(),
            'date': versaoSelecionada.find(".item-hora").text()
        };
        requestOperationState(that, 'fa-clock-o', true);
        console.log(json);
        $.ajax({
            url: '/post',
            data: JSON.stringify(json),
            dataType: 'text',
            contentType: "application/json; charset:UTF-8",
            type: "POST"
        }).done(function (data) {
            location.reload();
        });
    });
});