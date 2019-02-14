/*
 * Global variables
 */
var portfolios;
var isFirstScroll = true;

var MODULE_PATH;
var RESOURCE_URL;

var tradicionalNames = [
    "CLASS TRADICIONAL",
    "Alta Liquidez Recaudador",
    "Efectivo Colombia Pesos",
    "Strategist Liquidez Colombia"
];

var dinamicoNames = [
    "CLASS DINÁMICO",
    "Balanceado Conservador",
    "Diversificado Básico",
    "Strategist Moderado"
];

var accionColNames = [
    "CLASS ACCIÓN COLOMBIA",
    "Acciones en Pesos",
    "Acciones Colombia Pesos",
    "Acciones Colombia"
];

var rfiNames = [
    "CLASS INT. RENTA FIJA",
    "Renta Fija Dólares",
    "Renta Fija Internacional Dólar",
    "Renta Fija Global"
];

var rviNames = [
    "CLASS INT. RENTA VARIABLE",
    "Acciones en Dólares",
    "Acciones Internacionales Dólar",
    "Acciones USA"
];

var mixData = {
    finalRent: {
        colfondos: [],
        proteccion: [],
        porvenir: [],
        oldMutual: []
    },

    finalValues: {
        colfondos: [],
        proteccion: [],
        porvenir: [],
        oldMutual: []
    }
};

var jsonData;

var isMixEmpty;
var isGraphVisible = false;
var isMixGraphVisible = false;
var isTableVisible = false;

var portfoliosNames = {
    0: tradicionalNames,
    1: dinamicoNames,
    2: accionColNames,
    4: rfiNames,
    3: rviNames
};

var originalPosLegend;

/* Make sure DOM is ready */
$(function () {
    portfolios = [
        "tradicional",
        "dinamico",
        "accionCol",
        "rfi",
        "rvi"
    ];

    /* Detect changes on inputs inside table */
    $("td").on("change", "input", function () {
        var $tablesContainer = $(".container-table");
        var $table = $(this).parents("table");
        var $row = $(this).parents("tr");
        var $tBody = $table.children("tbody");
        var $tFoot = $table.children("tfoot");

        var $errorMessage = $(".tfoot-error span");

        var $resumeTab = $("#resume_mix_tab");
        var $tabButton = $("#resume_mix_button");

        var sum = 0;
        var rowIndex = $row.prop("rowIndex");
        var currentVal = $(this).val();

        for (var i = 0; i < 5; i++) {
            sum += parseInt($tBody.children().eq(i+1).find("input").val());
        }

        if ($(window).innerWidth() < 768) {
            $tablesContainer = $tablesContainer.find(".scrollable");
        } else {
            $tablesContainer = $tablesContainer.children();
        }

        /* Modify percents for the other AFPs tables */
        $.each($tablesContainer.children("table"), function (indexInArray)
        {
            if (indexInArray) {
                $(this).children().children("tr").eq(rowIndex).find("input").val(currentVal);
            }
        });

        if (sum < 100) {
            $errorMessage.text(Liferay.Language.get('herramientapv.error-msg.low'));
        } else if (sum > 100) {
            $errorMessage.text(Liferay.Language.get('herramientapv.error-msg.sup'));
        } else {
            // Hide foot error message
            $errorMessage.addClass("hidden");
            $errorMessage.text("");
            $tabButton.prop("disabled", false);
            $resumeTab.removeClass("nav-link-disabled");
            $resumeTab.addClass("nav-link");

            /* Update rents for the other AFPs tables */
            $.each($tablesContainer.children("table"), function () {
                updateRent($(this), true);
            });

            return;
        }

        // Show foot error message
        $errorMessage.removeClass("hidden");

        $tabButton.prop("disabled", true);
        $resumeTab.removeClass("nav-link");
        $resumeTab.addClass("nav-link-disabled");

        /* Update rents for the other AFPs tables */
        $.each($tablesContainer.children("table"), function () {
            updateRent($(this), false);
        });
    });

});

/**
 * Function to establish initial values to global variables
 * and bind html elements to certain events
 * @param contextPath {String}: web context
 * @param resourceUrl {String}: url to call Java method
 */
function initValues(contextPath, resourceUrl) {
    RESOURCE_URL = resourceUrl;

    MODULE_PATH = contextPath;

    isMixEmpty = true;

    /* Fills date select with twelve values */
    fillDateSelect();   // Default values dates

    /* Function to clear values in Firefox!! */
    refreshForm();
}

function loadCutDate(resourceUrl) {
    /* Require a resource to load cut date from WS */
    $.ajax({
        type: "POST",
        dataType: "json",
        url: resourceUrl,
        success: function(data) {
            fillDateSelect(data);
        }
    });
}

/**
 *
 * @param dataToSend
 * @param opt
 */
function sendData(dataToSend, opt) {
    opt = opt || "normal";
    $.ajax({
        type: "POST",
        url: RESOURCE_URL,
        dataType: "json",
        data: dataToSend,
        success: function(data) {
            // Hide loader
            var $loadPageDiv = $("#load-page");

            $loadPageDiv.css("display", "none");

            // Hide error message
            $(".error-container").removeClass("visible");

            jsonData = data;

            if (jsonData == "Error") {
                /* Hide tables */
                isTableVisible = false;

                $("#tables_container").removeClass("visible");
                $("#tables_mix_container").removeClass("visible");

                /* Show error message */
                $(".error-container").addClass("visible");
                return;
            }

            if (opt === "normal") {
                populateAFPTable(data);
                if (isGraphVisible) {
                    drawNormalGraphs();
                }
            } else {
                populateMixTables(data);


                if (isMixGraphVisible) {
                    onGraphsButtonClicked();
                } else {
                    populateResumeTables();
                }
                isMixEmpty = false;
            }

            if (($(window).innerWidth() < 767) && !switched ){
                updateTables();
            }

            isTableVisible = true;
        }
    });
}

/**
 *
 * @param data
 */
function populateAFPTable(data) {

    var options = {minimumFractionDigits: 2};
    var numFormat = Intl.NumberFormat("es-CO", options);
    var formatCurrency = Intl.NumberFormat("es-CO");

    var rentData = data["rent"];
    var salaryData = data["final_value"];

    /* Make tables visibles */
    $("#tables_container").addClass("visible");


    for(var i = 2; i < rentData.length; i++) {
        var portf = rentData[i];
        var salary = salaryData[i];

        /* Update column values */
        $("#row_colf_r td:nth-child("+(i)+")").text(numFormat.format(portf.colfondos) + "%");
        $("#row_proteccion_r td:nth-child("+(i)+")").text(numFormat.format(portf.proteccion) + "%");
        $("#row_porvenir_r td:nth-child("+(i)+")").text(numFormat.format(portf.porvenir) + "%");
        $("#row_oldMutual_r td:nth-child("+(i)+")").text(numFormat.format(portf.oldMutual) + "%");

        $("#row_colf_v td:nth-child("+(i)+")").text("$" + formatCurrency.format(salary.colfondos));
        $("#row_proteccion_v td:nth-child("+(i)+")").text("$" + formatCurrency.format(salary.proteccion));
        $("#row_porvenir_v td:nth-child("+(i)+")").text("$" + formatCurrency.format(salary.porvenir));
        $("#row_oldMutual_v td:nth-child("+(i)+")").text("$" + formatCurrency.format(salary.oldMutual));
    }
}

/**
 *
 * @param data
 */
function populateMixTables(data) {
    var numFormat = Intl.NumberFormat("es-CO");

    /* Make tables visibles */
    $("#tables_mix_container").addClass("visible");

    var colfValues = [0, 0, 0, 0, 0];
    var protValues = [0, 0, 0, 0, 0];
    var porvValues = [0, 0, 0, 0, 0];
    var oldMValues = [0, 0, 0, 0, 0];

    var $tColfBody = $("#table_colfondos tbody");
    var $tProtBody = $("#table_proteccion tbody");
    var $tPorvBody = $("#table_porvenir tbody");
    var $tOldMBody = $("#table_oldMutual tbody");

    var $rowColfPerc;
    var $rowProtPerc;
    var $rowPorvPerc;
    var $rowOldPerc;

    for(var i = 0; i < Object.keys(data).length; i++) {  // Loop for portfolios
        var subData = data[i];

        $rowColfPerc = $tColfBody.children().eq(1+i);
        $rowProtPerc = $tProtBody.children().eq(1+i);
        $rowPorvPerc = $tPorvBody.children().eq(1+i);
        $rowOldPerc = $tOldMBody.children().eq(1+i);

        /* Update column values */
        for(var j = 0; j < subData.length; j++) { // Loop for month periods
            var portf = subData[j];

            $rowColfPerc.children().eq(1+j).text(calcRNeta(portf.colfondos, j) + "%");
            $rowProtPerc.children().eq(1+j).text(calcRNeta(portf.proteccion, j) + "%");
            $rowPorvPerc.children().eq(1+j).text(calcRNeta(portf.porvenir, j) + "%");
            $rowOldPerc.children().eq(1+j).text(calcRNeta(portf.oldMutual, j) + "%");

            /* Calculates final rentability */
            colfValues[j] += portf.colfondos * ($rowColfPerc.children().eq(6).find("input").val()/100.0);
            protValues[j] += portf.proteccion * ($rowProtPerc.children().eq(6).find("input").val()/100.0);
            porvValues[j] += portf.porvenir * ($rowPorvPerc.children().eq(6).find("input").val()/100.0);
            oldMValues[j] += portf.oldMutual * ($rowOldPerc.children().eq(6).find("input").val()/100.0);
        }
    }

    var colfMixData = colfValues.map(function (value) {return salaryValue + (value * salaryValue);});

    var protMixData = protValues.map(function (value) {return salaryValue + (value * salaryValue)});

    var porvMixData = porvValues.map(function (value) {return salaryValue + (value * salaryValue)});

    var oldMixData = oldMValues.map(function (value) {return salaryValue + (value * salaryValue)});

    /* Set Final Rentability values percent */
    $rowColfPerc = $tColfBody.children().eq(6);
    $rowProtPerc = $tProtBody.children().eq(6);
    $rowPorvPerc = $tPorvBody.children().eq(6);
    $rowOldPerc = $tOldMBody.children().eq(6);

    var $rowColfPesos = $tColfBody.children().eq(7);
    var $rowProtPesos = $tProtBody.children().eq(7);
    var $rowPorvPesos = $tPorvBody.children().eq(7);
    var $rowOldPesos = $tOldMBody.children().eq(7);

    for (var i = 0; i < colfValues.length; i++) {
        $rowColfPerc.children().eq(1+i).text(calcRNeta(colfValues[i], i) + "%");
        $rowProtPerc.children().eq(1+i).text(calcRNeta(protValues[i], i) + "%");
        $rowPorvPerc.children().eq(1+i).text(calcRNeta(porvValues[i], i) + "%");
        $rowOldPerc.children().eq(1+i).text(calcRNeta(oldMValues[i], i) + "%");

        colfValues[i] = calcRNeta(colfValues[i], i, true);
        protValues[i] = calcRNeta(protValues[i], i ,true);
        porvValues[i] = calcRNeta(porvValues[i], i, true);
        oldMValues[i] = calcRNeta(oldMValues[i], i, true);

        $rowColfPesos.children().eq(1+i).text("$" + numFormat.format(colfMixData[i].toFixed(0)));
        $rowProtPesos.children().eq(1+i).text("$" + numFormat.format(protMixData[i].toFixed(0)));
        $rowPorvPesos.children().eq(1+i).text("$" + numFormat.format(porvMixData[i].toFixed(0)));
        $rowOldPesos.children().eq(1+i).text("$" + numFormat.format(oldMixData[i].toFixed(0)));
    }

    mixData.finalValues.colfondos = colfMixData;
    mixData.finalValues.proteccion = protMixData;
    mixData.finalValues.porvenir = porvMixData;
    mixData.finalValues.oldMutual = oldMixData;

    mixData.finalRent.colfondos = colfValues;
    mixData.finalRent.proteccion = protValues;
    mixData.finalRent.porvenir = porvValues;
    mixData.finalRent.oldMutual = oldMValues;

}

/**
 *
 */
function populateResumeTables() {
    var options = {minimumFractionDigits: 2};
    var numFormat = Intl.NumberFormat("es-CO", options);
    var currencyFormat = Intl.NumberFormat("es-CO");

    var $tProtBody = $("#t_resume_proteccion tbody");
    var $tPorvBody = $("#t_resume_porvenir tbody");
    var $tOldMBody = $("#t_resume_oldMutual tbody");

    var $rowProtPesos = $tProtBody.children().eq(0);
    var $rowProtPerc = $tProtBody.children().eq(1);

    var $rowPorvPesos = $tPorvBody.children().eq(0);
    var $rowPorvPerc = $tPorvBody.children().eq(1);

    var $rowOldPesos = $tOldMBody.children().eq(0);
    var $rowOldPerc = $tOldMBody.children().eq(1);

    var finalValues = mixData.finalValues;
    var finalRents = mixData.finalRent;

    for (var i = 0; i < mixData.finalValues.colfondos.length; i++) {
        var colfPesos = finalValues.colfondos[i];
        var colfRent = finalRents.colfondos[i];

        $rowProtPesos.children().eq(1+i).text("$" + currencyFormat.format((colfPesos - finalValues.proteccion[i]).toFixed(0)));
        $rowProtPerc.children().eq(1+i).text(numFormat.format((colfRent - finalRents.proteccion[i])) + "%");

        $rowPorvPesos.children().eq(1+i).text("$" + currencyFormat.format((colfPesos - finalValues.porvenir[i]).toFixed(0)));
        $rowPorvPerc.children().eq(1+i).text(numFormat.format((colfRent - finalRents.porvenir[i])) + "%");

        $rowOldPesos.children().eq(1+i).text("$" + currencyFormat.format((colfPesos - finalValues.oldMutual[i]).toFixed(0)));
        $rowOldPerc.children().eq(1+i).text(numFormat.format((colfRent - finalRents.oldMutual[i])) + "%");
    }
}

/**
 *
 * @param $table
 * @param isValid
 */
function updateRent($table, isValid) {
    var numFormat = Intl.NumberFormat("es-CO");

    var rentValues = [0, 0, 0, 0, 0];

    var $body = $table.children("tbody");
    var $row;
    var $rowPesos = $body.children().eq(7);

    var afpName = $table.attr("id").split("_")[1];


    if (isValid) {
        for(var i = 0; i < Object.keys(jsonData).length; i++) {  // Loop for portfolios
            var subData = jsonData[i];

            $row = $body.children().eq(1+i);

            /* Update column values */
            for(var j = 0; j < subData.length; j++) { // Loop for month periods
                var portf = subData[j];

                /* Calculates final rentability */
                rentValues[j] += portf[afpName] * ($row.children().eq(6).find("input").val()/100.0);
            }
        }

        /* Set Final Rentability values */
        $row = $body.children().eq(6);
        var salaryValues = rentValues.map(function (value) {return salaryValue + (value * salaryValue);});

        for (var i = 0; i < rentValues.length; i++) {
            $row.children().eq(1+i).text(calcRNeta(rentValues[i], i) + "%");
            $rowPesos.children().eq(1+i).text("$" + numFormat.format(salaryValues[i].toFixed(0)));
        }

        mixData.finalValues[afpName] = salaryValues;
        mixData.finalRent[afpName] = rentValues.map(function (value, index) { return calcRNeta(value, index, true); });

    } else {
        $row = $body.children().eq(6);

        for (var i = 0; i < rentValues.length; i++) {
            $row.children().eq(1+i).text("\u2014");
            $rowPesos.children().eq(1+i).text("\u2014");
        }
    }
}

/**
 *
 * @param value
 * @param index
 * @param noFormat
 * @returns {*}
 */
function calcRNeta(value, index, noFormat) {
    noFormat = noFormat || false;

    var options = {minimumFractionDigits: 2};
    var numFormat = Intl.NumberFormat("es-CO", options);

    var divider = 12 + 12*index;

    if (noFormat) {
        return ((Math.pow(1 + value, 12 / divider) - 1) * 100).toFixed(2);
    } else {
        return numFormat.format( ((Math.pow(1 + value, 12 / divider) - 1) * 100).toFixed(2) );
    }

}

/**
 *
 */
function resumeMixTabClick() {
    $(".container-table").css("display", "none");
    $(".container-resume").css("display", "block");

    onGraphsButtonClicked();
}

/**
 *
 */
function tablesMixTabClicked() {
    isMixGraphVisible = false;

    $(".container-table").css("display", "block");
    $(".container-resume").css("display", "none");
}

/**
 *
 */
function showGraphs() {
    /* Get legend original position */
    originalPosLegend = $("#first_graph").offset().top;

    isGraphVisible = true;

    $(".container-table").css("display", "none");
    $(".container-graphs").css("display", "block");

    drawNormalGraphs();
}

/**
 *
 */
function showNormalTables() {
    isGraphVisible = false;

    $(".container-table").css("display", "block");
    $(".container-graphs").css("display", "none");
}

/**
 *
 */
function onTableButtonClicked() {
    isMixGraphVisible = false;

    /* Switch views */
    $(".tables-container").css("display", "block");
    $(".graphics-container").css("display", "none");

    /* Switch active buttons */
    $("#graphs_button").removeClass("button-active");
    $("#graphs_button").addClass("button-no-active");
    $("#tables_button").removeClass("button-no-active");
    $("#tables_button").addClass("button-active");

    populateResumeTables();
}

/**
 *
 */
function onGraphsButtonClicked() {
    /* Switch views */
    $(".tables-container").css("display", "none");
    $(".graphics-container").css("display", "block");

    /* Switch active buttons */
    $("#graphs_button").removeClass("button-no-active");
    $("#graphs_button").addClass("button-active");
    $("#tables_button").removeClass("button-active");
    $("#tables_button").addClass("button-no-active");

    var protBarData = {
        label: "Protección",
        data: mixData.finalValues.proteccion,
        color: "rgba(239, 200, 70, 1)"
    };

    var porvBarData = {
        label: "Porvenir",
        data: mixData.finalValues.porvenir,
        color: "rgba(205, 110, 51,1)"
    };

    var oldMBarData = {
        label: "Old Mutual",
        data: mixData.finalValues.oldMutual,
        color: "rgba(0, 102, 53, 1)"
    };

    drawDoubleBarChart(protBarData, $("#chart_prot"));
    drawDoubleBarChart(oldMBarData, $("#chart_old"));
    drawDoubleBarChart(porvBarData, $("#chart_porv"));

    isMixGraphVisible = true;
}

/**
 *
 */
function drawNormalGraphs() {
    /* Sets legend to initial position */
    $("#legend_bar_container").css("top", "0");

    /* Reset scroll */
    isFirstScroll = true;

    if (isAfpEmpty) {
        drawLineChart(jsonData["final_value"], monthPeriod);
        drawBarChart(jsonData, monthPeriod, undefined, false);
        drawScatterChart(jsonData, monthPeriod);                // Rent vs. Volatility
        drawScatterChart(jsonData, monthPeriod, true);          // Max Draw Down
    } else {
        drawLineChart(jsonData["final_value"], monthPeriod, afpName);
        drawBarChart(jsonData, monthPeriod, afpName, false);
        drawScatterChart(jsonData, monthPeriod, false, afpName);        // Rent vs. Volatility
        drawScatterChart(jsonData, monthPeriod, true, afpName);         // Max DrawDown
    }

}


$(window).scroll(function(){
    var $legend = $("#legend_bar_container");

    //$legend.stop().animate({"marginTop": ($(window).scrollTop()) + "px", "marginLeft":($(window).scrollLeft()) + "px"}, "slow" );

    /* If not in Graph view not scroll event is needed */
    if (!isGraphVisible) return;

    if (isFirstScroll) {
        originalPosLegend = $legend.offset().top;
        isFirstScroll = false;
    }

    var value = $(window).scrollTop() - (originalPosLegend  - 150);

    if (value > 1750) return;

    if (value > 0) {
        $legend.stop().animate({"top": value + "px"}, "fast" );
    }
});

/**
 *
 * @param afpName
 */
function hideRows(afpName) {
    var $tRentsBody = $("#table_rents_normal tbody");
    var $tFinalVBody = $("#table_finalv tbody");

    var $tRentsPinned;
    var $tFinalVPinned;

    if (window.innerWidth < 768) {
        $tRentsPinned = $(".pinned #table_rents_normal tbody");
        $tFinalVPinned = $(".pinned #table_finalv tbody");
    }

    $tRentsBody.find(".no-colf-row").addClass("hidden");
    $tFinalVBody.find(".no-colf-row").addClass("hidden");

    for(var i = 2; i < 5; i++) {
        var $rowRent = $tRentsBody.children().eq(i);
        var $rowFinalV = $tFinalVBody.children().eq(i);

        if ($rowRent.attr("id").toString().indexOf(afpName) > -1) {
            $rowRent.removeClass("hidden");
            $rowRent.addClass("visible-row");
        }

        if ($rowFinalV.attr("id").toString().indexOf(afpName) > -1) {
            $rowFinalV.removeClass("hidden");
            $rowFinalV.addClass("visible-row");
        }

        // Show rows of pinned table
        if (window.innerWidth < 768) {
            var $rowRentPinned = $tRentsPinned.children().eq(i);
            var $rowFinalVPinned = $tFinalVPinned.children().eq(i);

            if ($rowRentPinned.attr("id").toString().indexOf(afpName) > -1) {
                $rowRentPinned.removeClass("hidden");
                $rowRentPinned.addClass("visible-row");
            }

            if ($rowFinalVPinned.attr("id").toString().indexOf(afpName) > -1) {
                $rowFinalVPinned.removeClass("hidden");
                $rowFinalVPinned.addClass("visible-row");
            }
        }
    }
}

/**
 *
 */
function showRows() {
    $("#tables_container").find(".no-colf-row").removeClass("hidden");
    $("#tables_container").find(".no-colf-row").addClass("visible-row");
}

/**
 *
 * @param afpName
 */
function hideMixTables(afpName) {
    var $tablesContainer = $(".container-table");

    $tablesContainer.find(".no-colf").addClass("hidden");

    for(var i = 1; i < 4; i++) {
        //var $table = $tablesContainer.children().eq(i);
        var $table = $tablesContainer.find("."+afpName);

        //if ($table.attr("id").toString().includes(afpName)) {
            $table.removeClass("hidden");
            //$table.addClass("visible-table");
        //}
    }
}

/**
 *
 */
function showAFPMixTables() {
    $(".container-table").find(".no-colf").removeClass("hidden");
    //$(".container-table").find(".no-colf").addClass("visible-table");
}

/**
 *
 * @param afpName
 */
function hideResumeGraphsAndTables(afpName) {
    var $graphsContainer = $(".graphics-container");
    var $tablesContainer = $(".tables-container");

    $graphsContainer.children().addClass("hidden");
    $tablesContainer.children().addClass("hidden");

    for(var i = 0; i < 3; i++) {
        var $graph = $graphsContainer.children().eq(i);
        var $table = $tablesContainer.children().eq(i);

        if ($graph.attr("id").toString().indexOf(afpName) > -1) {
            $graph.removeClass("hidden");
            $graph.addClass("visible");
        }

        /*if (window.innerWidth < 768) {
            if ($table.find("table").attr("id").toString().indexOf(afpName) > -1) {
                $table.removeClass("hidden");
                //$table.addClass("visible");
            }
        } else {
            if ($table.attr("id").toString().indexOf(afpName) > -1) {
                $table.removeClass("hidden");
                $table.addClass("visible-table");
            }
        }*/
        if ($table.hasClass(afpName)) {
            $table.removeClass("hidden");
        }

    }
}

/**
 *
 */
function showResumeGraphsAndTables() {
    $(".graphics-container").children().removeClass("hidden");
    $(".graphics-container").children().addClass("visible");

    $(".tables-container").children().removeClass("hidden");
    //$(".tables-container").children().addClass("visible-table");
}

/**
 *
 * @param cutDate
 */
function fillDateSelect(cutDate) {
    var $select = $("#date_input");

    $select.html('<option value="" disabled="" selected="">' + Liferay.Language.get("herramientapv.label.cut-date")+ '</option>');

    var myDate = new Date(2017, 11, 31);        // Date with one month before cut date

    if (typeof cutDate !== 'undefined') {

        var cutYear = cutDate.substr(0,4),
            cutMonth = cutDate.substr(5,2) - 1,
            cutDay = cutDate.substr(8,2);

        myDate = new Date(cutYear, cutMonth, cutDay);

    }

    var year = myDate.getFullYear();
    var month = myDate.getMonth();

    for(var i = 11; i >= 0; i--) {
        myDate = new Date(year, (month+1) - i, 0);

        var tmpYear = myDate.getFullYear();
        var tmpMonth = myDate.getMonth();
        var tmpDayMonth = myDate.getDate();

        tmpMonth += 1;

        if (tmpMonth.toString().length === 1) {
            tmpMonth = "0" + tmpMonth;
        }

        $select.append($('<option>', {
            value: tmpYear + "-" + (tmpMonth) + "-" + tmpDayMonth,
            text: tmpDayMonth + "/" + (tmpMonth) + "/" + tmpYear
        }));
    }
}

/**
 *
 */
function changeNormalTablesLabels() {
    var $tBody = $("#table_rents_normal tbody"); // Rents table

    if (window.innerWidth < 768) {
        $tBody = $(".pinned #table_rents_normal tbody");
    }

    $tBody.children().eq(1).children().eq(0).replaceWith("<td>Colfondos<br>" + portfoliosNames[projectionValue][0] + "</td>"); // Colfondos
    $tBody.children().eq(2).children().eq(0).replaceWith("<td>Protección<br>" + portfoliosNames[projectionValue][1] + "</td>"); // Protección
    $tBody.children().eq(3).children().eq(0).replaceWith("<td>Old Mutual<br>" + portfoliosNames[projectionValue][3] + "</td>"); // Old Mutual
    $tBody.children().eq(4).children().eq(0).replaceWith("<td>Porvenir<br>" + portfoliosNames[projectionValue][2] + "</td>"); // Porvenir

    $tBody = $("#table_finalv tbody"); // Final value table

    if (window.innerWidth < 768) {
        $tBody = $(".pinned #table_finalv tbody");
    }

    $tBody.children().eq(1).children().eq(0).replaceWith("<td>Colfondos<br>" + portfoliosNames[projectionValue][0] + "</td>"); // Colfondos
    $tBody.children().eq(2).children().eq(0).replaceWith("<td>Protección<br>" + portfoliosNames[projectionValue][1] + "</td>"); // Protección
    $tBody.children().eq(3).children().eq(0).replaceWith("<td>Old Mutual<br>" + portfoliosNames[projectionValue][3] + "</td>"); // Old Mutual
    $tBody.children().eq(4).children().eq(0).replaceWith("<td>Porvenir<br>" + portfoliosNames[projectionValue][2] + "</td>"); // Porvenir
}

/* Redraws charts on rezise */
$(window).on("resize", function () {
    if (isGraphVisible) {
        drawNormalGraphs();
    }
});