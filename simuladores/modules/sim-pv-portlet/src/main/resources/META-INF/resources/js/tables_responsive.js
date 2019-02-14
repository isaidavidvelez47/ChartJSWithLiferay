var switched = false;

$(function() {

    $(window).on("load", updateTables);
    $(window).on("resize", updateTables);

});

var updateTables = function() {
    if (($(window).innerWidth() < 767) && !switched ){
        switched = true;
        $("table.responsive").each(function(i, element) {
            splitTable($(element));
        });
        return true;
    }
    else if (switched && ($(window).innerWidth() > 767)) {
        switched = false;
        $("table.responsive").each(function(i, element) {
            unsplitTable($(element));
        });
    }
};

function splitTable(original)
{
    original.wrap("<div class='table-wrapper' />");

    var copy = original.clone();
    copy.find("td:not(:first-child), th:not(:first-child)").css("display", "none");
    copy.removeClass("responsive");

    original.closest(".table-wrapper").append(copy);
    copy.wrap("<div class='pinned' />");
    original.wrap("<div class='scrollable' />");
}

function unsplitTable(original) {
    original.closest(".table-wrapper").find(".pinned").remove();
    original.unwrap();
    original.unwrap();
}