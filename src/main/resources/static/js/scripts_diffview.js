/**
 * Created by guilherme on 26/05/17.
 */
function calcularDiferencasEntreVersoes(viewType) {
    'use strict';
    var diffoutputdiv = document.getElementById("diffoutput");

    var afterSelected = $(".selected").next();
    var previous = existAfterElement(afterSelected)?
        difflib.stringAsLines($.base64.decode(afterSelected.find(".item-conteudo").text())): "";
    var actual = difflib.stringAsLines(CKEDITOR.instances['ckeditor'].getData());

    var sequenceMatcher = new difflib.SequenceMatcher(previous, actual);
    var opcodes = sequenceMatcher.get_opcodes();

    while (diffoutputdiv.firstChild) diffoutputdiv.removeChild(diffoutputdiv.firstChild);

    diffoutputdiv.appendChild(diffview.buildView({
        baseTextLines: previous,
        newTextLines: actual,
        opcodes: opcodes,
        // set the display titles for each resource
        baseTextName:existAfterElement(afterSelected)? moment(afterSelected.find(".item-hora").text()).format('llll') : "-",
        newTextName: moment($(".selected").find(".item-hora").text()).format('llll'),
        contextSize: null,
        viewType: viewType// inline 1 - sideBySide 0
    }));
};

function existAfterElement(afterElement) {
    return afterElement.length >= 1 ? true : false;
}