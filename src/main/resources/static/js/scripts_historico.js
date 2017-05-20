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
                            .addClass("atual");
    carregarInformacoesDeRevisao(revisaoAtual);
    applyAnimations();

    $(".historico-revision-group-item").on("click", function () {
        var revisao= $(this);
        carregarInformacoesDeRevisao(revisao);
    });
    
    function carregarInformacoesDeRevisao(revisao){
        var titulo = revisao.find(".item-titulo").text();
        var dataModificacao = revisao.find(".item-hora").text();
        var conteudo = revisao.find(".item-conteudo").text();
        $("#document_name_data").text(titulo+", "+moment(dataModificacao).calendar());
        CKEDITOR.instances['ckeditor'].setData($.base64.decode(conteudo));
        selecionarRevisao(revisao);
    }
    
    function selecionarRevisao(revisao) {
        $(".selected").removeClass("selected");
        revisao.addClass("selected");
    }

    function applyAnimations () {
        $('#btn-restore-document').animateCss('fadeInRight');
    };
});