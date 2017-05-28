/**
 * Created by guilherme on 13/05/17.
 */
$(document).ready(function() {
    'use strict';

    $.get('/last',function (data) {
        if(data){updateViewContent(data)};
        applyAnimations();
    });

    $('#btn-save-document').on('click', function () {
        var that = $(this);
        var editorContent = CKEDITOR.instances['ckeditor'].getData();
        var documentName = $("#document_name").val();
        documentName = documentName ? documentName : 'Documento sem nome';
        var json = {
            'name': documentName,
            'data': $.base64.encode(editorContent),
            'date': moment(),
            'editor' : EDITOR.name,
            'version': EDITOR.version
        };

        requestOperationState(that, 'fa-floppy-o', true);

        $.ajax({
            url: '/post',
            data: JSON.stringify(json),
            dataType: 'text',
            contentType: "application/json; charset:UTF-8",
            type: "POST"
        }).done(function (data) {
            updateViewContent(JSON.parse(data));
        }).always(function () {
            requestOperationState(that, 'fa-floppy-o', false);
        });
    });
    
    function updateViewContent (data) {
        $("#document_message").html("Última edição ocorreu "+ moment(data.dataModificacao).fromNow());
        $("#document_name").val(data.titulo);
        $("#document_name_label").addClass("active");
        $("#ckeditor").val($.base64.decode(data.conteudo));
    };

    function applyAnimations () {
        $('#btn-save-document').animateCss('fadeInRight');
        $('#document_message').animateCss('fadeIn');
        $('.brand > i').animateCss('flipInY');
    };
});