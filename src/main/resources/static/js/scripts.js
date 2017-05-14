/**
 * Created by guilherme on 13/05/17.
 */
$(document).ready(function() {
    'use strict';
    var instance = CKEDITOR.replace('ckeditor');

    $.get('/last',function (data) {
        if(data) {
            updateViewContent(data);
            applyAnimations();
        }
    });

    $('#btn-save-document').on('click', function () {
        var that = $(this);
        var editorContent = CKEDITOR.instances['ckeditor'].getData();
        var documentName = $("#document_name").val();
        documentName = documentName ? documentName : 'Documento sem nome';
        var date = new Date();
        var json = {
            'name': documentName,
            'data': $.base64.encode(editorContent),
            'date': date.toDateString() + " " + date.toTimeString()
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

    var requestOperationState = function (el, elIconClass, onSend) {
        if(onSend){
            el.addClass("disabled");
            var icon = el.find("i");
            icon.removeClass(elIconClass);
            icon.addClass("fa-hourglass-o");
            el.find("span").text(el.data("loading-text"));
        }else{
            setTimeout(function () {
                var span = el.find("span");
                span.text(span.data("text"));
                var icon = el.find("i");
                icon.removeClass("fa-hourglass-o");
                icon.addClass(elIconClass);
                el.removeClass("disabled");
            },1000);
        }
    }
    
    var updateViewContent = function (data) {
        var date = new Date(data.dataModificacao);
        $("#document_message").html("Salvo em "
            + date.toDateString() + " " + date.toTimeString().substr(0, 9));
        $("#document_name").val(data.titulo);
        $("#document_name_label").addClass("active");
        $("#ckeditor").val($.base64.decode(data.conteudo));
    };

    var applyAnimations = function () {
        $('#btn-save-document').animateCss('fadeInRight');
        $('#document_message').animateCss('fadeIn');
        $('.brand > i').animateCss('flipInY');
    };

    $.fn.extend({
        animateCss: function (animationName) {
            var animationEnd = 'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend';
            this.addClass('animated ' + animationName).one(animationEnd, function() {
                $(this).removeClass('animated ' + animationName);
            });
        }
    });
});