/**
 * Created by guilherme on 13/05/17.
 */
$(document).ready(function() {
    'use strict';
    var instance = CKEDITOR.replace('ckeditor');

    $.get('/last',function (data) {
        if(data) {
            updateViewContent(data);
        }
    });

    $('#btn-save-document').on('click', function () {
        var editorContent = CKEDITOR.instances['ckeditor'].getData();
        var documentName = $("#document_name").val();
        documentName = documentName ? documentName : 'Documento sem nome';
        var date = new Date();
        var json = {
            'name': documentName,
            'data': $.base64.encode(editorContent),
            'date': date.toDateString() + " " + date.toTimeString()
        };

        $.ajax({
            url: '/post',
            data: JSON.stringify(json),
            dataType: 'text',
            contentType: "application/json; charset:UTF-8",
            type: "POST"
        }).done(function (data) {
            updateViewContent(JSON.parse(data));
        });
    });
    
    var updateViewContent = function (data) {
        var date = new Date(data.dataModificacao);
        $("#document_message").html("Salvo em "
            + date.toDateString() + " " + date.toTimeString().substr(0, 9));
        $("#document_name").val(data.titulo);
        $("#document_name_label").addClass("active");
        $("#ckeditor").val($.base64.decode(data.conteudo));
    }
});