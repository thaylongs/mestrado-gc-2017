/**
 * Created by guilherme on 20/05/17.
 */
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