/**
 * Created by guilherme on 20/05/17.
 */
var EDITOR = {
    name: "CKEditor",
    version: CKEDITOR.version+"-"+CKEDITOR.revision
};

$(document).ready(function() {
    'use strict';
    CKEDITOR.replace('ckeditor');
    moment.locale('pt-br');

    $.fn.extend({
        animateCss: function (animationName) {
            var animationEnd = 'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend';
            this.addClass('animated ' + animationName).one(animationEnd, function() {
                $(this).removeClass('animated ' + animationName);
            });
        }
    });
});

function requestOperationState (el, elIconClass, onSend) {
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