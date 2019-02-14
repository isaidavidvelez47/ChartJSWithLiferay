var chartJS;

Liferay.Loader.require('chart-js', function (chartJs) {
    chartJS = chartJs;
});

/*
 * Global variables
 */
var hasInitParams = false;
var portfolioName;
var portfolioData;
var graphsData;
var brands;
var isGraphPresent = false;
var tabSelected;
var maxPensionAge;
var ageOld;
var afpSelected;
var FEMALE_AGE;
var MALE_AGE;
var MODULE_PATH;

/* Make sure DOM is ready */
jQuery(function () {
    brands = [
        "colfondos",
        "porvenir",
        "proteccion",
        "old mutual"
    ];

});

/**
 * Function to establish initial values to global variables
 * and bind html elements to certain events
 * @param malePensionAge
 * @param femalePensionAge
 */
function initValues(malePensionAge, femalePensionAge, contextPath) {
    FEMALE_AGE = femalePensionAge;
    MALE_AGE = malePensionAge;

    maxPensionAge = FEMALE_AGE - 1;

    afpSelected = false;
    $ageInput = $("#age_input");

    MODULE_PATH = contextPath;

    $("#female_img").attr("src", MODULE_PATH + "/assets/icono_mujer_activa.png");
    $("#male_img").attr("src", MODULE_PATH + "/assets/icono_hombre.png");
    $("#tableTextId").attr("value", "mujer");

    /* Animate label of select elements*/
    $("select").on("change", function() {
        var $span = $(this).next("span");
        if (!$span.hasClass("span-active")) {
            $span.addClass("span-active");
            $(this).css({"color": "black", "font-family": "open_sansregular"});
        }
    });

    $("#projection_select").on("change", function () {
        var $selectedProjection = $("#projection_select option:selected");
        var $span = $("#projection_description");
        var $disclaimer = $(".disclaimer-container");

        /* Set disclaimer visible*/
        $disclaimer.removeClass("hidden");
        $disclaimer.addClass("visible");

        $span.css("visibility", "visible");

        switch ($selectedProjection.text()) {
            case "Actual":
                $span.text(Liferay.Language.get('herramientapo.form.text.info.actual'));
                break;
            case "Esperada":
                $span.text(Liferay.Language.get('herramientapo.form.text.info.esperada'));
                break;
            case "Optimista":
                $span.text(Liferay.Language.get('herramientapo.form.text.info.optimista'));
                break;
            case "Conservadora":
                $span.text(Liferay.Language.get('herramientapo.form.text.info.conservadora'));
                break;
        }
    });

    /* Parse input currencies to colombian money */
    $(".currency_input").on( "keyup", ":input", function( event ) {

        // 1.
        var selection = window.getSelection().toString();
        if ( selection !== "" ) {
            return;
        }

        // 2.
        if ( $.inArray( event.keyCode, [38,40,37,39] ) !== -1 ) {
            return;
        }

        // 1
        var $this = $( this );
        var input = $this.val();
        var numberFormat = new Intl.NumberFormat("es-CO");

        // 2
        input = input.replace(/[\D\s\._\-]+/g, "");

        // 3
        input = input ? parseInt( input, 10 ) : 0;

        // 4
        $this.val( function() {
            return ( input === 0 ) ? "" : numberFormat.format(input);
        } );

    } );

    /* Avoid arrow keys to update age field value */
    $ageInput.on("focus", function () {
       $(this).on("keydown", function () {
           ageOld = $(this).val();  // Previous value in field

           if (event.keyCode === 38 || event.keyCode === 40) {
               event.preventDefault();
           }
       });
    });

    /* Parse age input so that only values between 18 and maxPensionAge are allowed */
    $ageInput.on("keyup", function (event) {
        // 1.
        var selection = window.getSelection().toString();
        if ( selection !== "" ) {
            return;
        }

        // 2.
        if ( $.inArray( event.keyCode, [38,40,37,39] ) !== -1 ) {
            return;
        }

        // 1
        var $this = $( this );
        var input = $this.val();


        if ( input === "" && $.inArray(event.which, [46, 107,109,110,190,229]) === -1 ) {
            return;
        }

        // 2
        input = input.toString().replace(/[\D\s\._\-]+/g, "");

        // 3
        input = input ? parseInt( input, 10 ) : 0;

        // 4
        if (input.toString().length === 1 && input > (maxPensionAge / 10) || input < 1) {
            input = 0;
            //console.log("len: 1");
        } else if (input.toString().length === 2 && (input > maxPensionAge || input < 18) ) {
            input = 0;
            //console.log("len: 2");
        } else if (input.toString().length >= 3){
            input = 0;
            //console.log("len: 3");
        }

        // 5
        $this.val( function() {
            return ( input === 0 || event.which === 229 ) ? ageOld : input;
        } );

        if ($this.val() >= 18 && $this.val() <= maxPensionAge) {
            $("#age_slider").val($this.val());
        }

    });

    $ageInput.on("focus", function () {
        $(this).next("span").css({"top": "-25px", "opacity": "1"});
    });

    $ageInput.on("focusout", function () {
       if ($(this).val() === "") {
           $(this).next("span").css({"top": "0", "opacity": "0"});
       }
    });

    /* Update slider value */
    $("#age_slider").on("input change", function () {
        var $ageInput = $("#age_input");

        $ageInput.val($(this).val());

        //$ageInput.focus();
        $ageInput.next("span").css({"top": "-25px", "opacity": "1"});
    });


}

/**
 * Function to process data coming in JSON format
 *
 * @param response: input with data in JSON format
 */
function processJson(response){
    // If enter this function graph is present
    isGraphPresent = true;

    //Parse JSON
    graphsData = $.parseJSON(response);
    //console.log(graphsData);
    //console.log(graphsData.contributions[0]);
    $.each(graphsData, function (key, value) {
        if (key.length > 15) {
            portfolioData = value;
            portfolioName = key;
        }
    });

    var tempName = portfolioName.split(" ");
    if (tempName[0] === "Proteccion") {
        tempName[0] = "Protección";
        portfolioName = tempName.join(" ");
    }

    onTimeChartButtonClicked();
}

/**
 * Function to draw line graphs based on
 * @param data: containing colfondos, porvenir, proteccion, old_mutual
 * @param labels: dates to show in x axis
 * @param portfolioName: AFP name
 * @param portfolioData: AFP values to plot
 */
function drawLineChart(data, labels, portfolioName, portfolioData) {
    resetCanvas();
    resetLegend();

    var ctx;
    var $canvas = $("#myChart");
    if( $canvas.length ) ctx = $canvas.get(0).getContext("2d");
    else console.log("Error: Canvas not found with selector #canvas");

    var yTitle = Liferay.Language.get('herramientapo.graphs-1.y-title') + " (Millones)";

    var colfondosGraph = {
        label: "Colfondos Mayor Riesgo",
        data: data.Colfondos,
        lineTension: 0.3,
        fill: true,
        backgroundColor: "rgba(0, 160, 234,0.2)",
        borderColor: "rgb(0, 160, 234)",
        pointBorderColor: "white",
        pointBackgroundColor: "rgb(0, 160, 234)",
        pointRadius: 4,
        pointHoverRadius: 10
    };

    var porvenirGraph = {
        label: "Porvenir Mayor Riesgo",
        data: data.Porvenir,
        lineTension: 0.3,
        fill: true,
        backgroundColor: "rgba(205, 110, 51,0.2)",
        borderColor: "rgb(205, 110, 51)",
        pointBorderColor: "white",
        pointBackgroundColor: "rgb(205, 110, 51)",
        pointRadius: 4,
        pointHoverRadius: 10
    };

    var proteccionGraph = {
        label: "Protección Mayor Riesgo",
        data: data.Proteccion,
        lineTension: 0.3,
        fill: false,
        backgroundColor: "rgba(239, 200, 70, 0.2)",
        borderColor: "rgb(239, 200, 70)",
        pointBorderColor: "white",
        pointBackgroundColor: "rgb(239, 200, 70)",
        pointRadius: 4,
        pointHoverRadius: 10
    };

    var oldMutualGraph = {
        label: "Old Mutual Mayor Riesgo",
        data: data["Old Mutual"],
        lineTension: 0.3,
        fill: false,
        backgroundColor: "rgba(0, 102, 53, 0.2)",
        borderColor: "rgb(0, 102, 53)",
        pointBorderColor: "white",
        pointBackgroundColor: "rgb(0, 102, 53)",
        pointRadius: 4,
        pointHoverRadius: 10
    };

    var portfolioGraph = {
        label: portfolioName,
        data: portfolioData,
        lineTension: 0.3,
        borderDash: [10, 5],
        borderWidth: 3,
        fill: false,
        backgroundColor: "transparent",
        borderColor: "red",
        pointBorderColor: "red",
        pointBackgroundColor: "white",
        pointRadius: 4,
        pointHoverRadius: 10
    };

    var graphData = {
        labels: labels,
        datasets: [
            colfondosGraph,
            porvenirGraph,
            proteccionGraph,
            oldMutualGraph,
            portfolioGraph
        ]
    };

    var chartOptions = {
        responsive: true,
        maintainAspectRatio: false,
        scales: {
            yAxes: [{
                ticks: {
                    display: true,
                    fontSize: 11,
                    fontStyle: "bold",
                    beginAtZero:true,
                    callback: function(value) {
                        var numberFormat = new Intl.NumberFormat("es-CO");
                        return "$" + numberFormat.format(value/1000000);
                    }
                },
                scaleLabel: {
                    display: true,
                    labelString: yTitle,
                    fontStyle: "bold",
                    fontSize: "11"
                }
            }],
            xAxes: [{
                ticks: {
                    fontSize: 11,
                    fontStyle: "bold"
                }
            }]
        },
        tooltips: {
            enabled: true,
            callbacks: {
                label: function(tooltipItems, data) {
                    var numberFormat = new Intl.NumberFormat("es-CO");

                    return " " + numberFormat.format((tooltipItems.yLabel/1000000).toFixed(3)) + " (Millones)";
                }
            },
            bodyFontSize: (window.innerWidth < 600) ? 12:13
        },
        legend: false,
        legendCallback: function(chart) {
            var legendHtml = [];
            legendHtml.push("<ul class='labels-five'>");
            var item = chart.data.datasets;
            for (var i=0; i < item.length; i++) {
                legendHtml.push("<li>");
                legendHtml.push('<span class="chart-legend" style="background-color:' + item[i].borderColor +'"></span>');
                legendHtml.push('<span class="chart-legend-label-text">' + item[i].label +'</span>');
                legendHtml.push("</li>");
            }

            legendHtml.push("</ul>");
            return legendHtml.join("");
        }
    };

    var myChart = new chartJS.Chart(ctx, {
        type: "line",
        data: graphData,
        options: chartOptions
    });

    $("#legend_container").html(myChart.generateLegend());
}

/**
 * Function to draw horizontal or vertical bar chart depending on
 * @param orientation: indicates whether bar chart is vertical or horizontal
 * @param originalData: data to plot
 * @param labels: labels for every bar
 * @param contributions: contributions reached by pension time
 */
function drawBarChart(originalData, labels, orientation, contributions) {
    if (typeof contributions === 'undefined') {
        contributions = false;
    }

    resetCanvas();
    resetLegend();

    chartJS.Chart.defaults.global.defaultFontFamily = "open_sansregular";
    chartJS.Chart.defaults.global.defaultFontSize = 14;

    var ctx;
    var $canvas = $("#myChart");
    if( $canvas.length ) {
        ctx = $canvas.get(0).getContext("2d");
        ctx.clearRect(0, 0, $canvas.width, $canvas.height);
    }
    else {
        console.log("Error: Canvas not found with selector #canvas");
        return;
    }

    var type = "bar";
    var yAxisTitle = Liferay.Language.get('herramientapo.graphs-2.title') + " (Millones)";
    if (window.innerWidth <= 470) {
        yAxisTitle = Liferay.Language.get('herramientapo.graphs-2.title') + " (Millones)";
    }
    var xAxisTitle = Liferay.Language.get('herramientapo.graphs-bars.x-title');
    var graphLabel = "Comparación saldos CAI al momento de pensión";
    var backgroundColor = [
        "rgba(0, 160, 234,1)",
        "rgba(205, 110, 51,1)",
        "rgba(239, 200, 70, 1)",
        "rgba(0, 102, 53, 1)",
        "rgba(255, 0, 0, 1)"
    ];

    var maxValues;
    var ticksY = {
        display: true,
        fontSize: 11,
        fontStyle: "bold",
        beginAtZero:true,
        callback: function(value, index, values) {
            maxValues = values;
            var numberFormat = new Intl.NumberFormat("es-CO");

            return "$" + numberFormat.format(value/1000000);

        }
    };

    var ticksX = {
        display: false,
        fontSize: (window.innerWidth >= 950) ? 10 : 11,
        fontStyle: "bold"
    };

    var tempTicks;

    var offsetY = 32;
    var offsetX = 8;
    var events = ["click", "touchstart", "touchmove", "touchend"];

    if (orientation !== "vertical"){
        type = "horizontalBar";
        events = [];
        graphLabel = "Costo debido al tiempo en tomar decisión";
        backgroundColor = [
            "rgba(205, 110, 51, 1)",
            "rgba(239, 200, 70, 1)",
            "rgba(0, 102, 53, 1)",
            "rgba(255, 0, 0, 1)"
        ];
        tempTicks = ticksX;
        ticksX = ticksY;
        ticksY = tempTicks;
        ticksY["display"] = false;
        ticksY["fontSize"] = 14;
        ticksX["display"] = true;
        yAxisTitle = Liferay.Language.get('herramientapo.graphs-bars.x-title');
        xAxisTitle = Liferay.Language.get('herramientapo.graphs-3.title') + " (Millones)";
        offsetY = -5;
        offsetX = 100;
    }

    var contributionsData = {
        label: "Aportes",
        data: [contributions, contributions, contributions, contributions, contributions],
        backgroundColor: ["#91AFAF", "#AF9D8B", "#AFAF92", "#546652", "#9F8C86"]
    };

    var graphData = {
        label: graphLabel,
        data: originalData,
        backgroundColor: backgroundColor
    };


    var data = {
        labels: labels,
        datasets: [graphData]
    };

    if (orientation === "vertical") {
        graphData = {
            label: "Rendimiento",
            data: [
                originalData[0] - contributions,
                originalData[1] - contributions,
                originalData[2] - contributions,
                originalData[3] - contributions,
                originalData[4] - contributions
            ],
            backgroundColor: backgroundColor
        };

        data = {
            labels: labels,
            datasets: [contributionsData, graphData]
        }
    }

    var chartOptions = {
        responsive: true,
        maintainAspectRatio: false,
        scales: {
            yAxes: [{
                stacked: orientation === "vertical",
                ticks: ticksY,
                scaleLabel: {
                    display: true,
                    labelString: yAxisTitle,
                    fontStyle: "bold",
                    fontSize: "11"
                }
            }],
            xAxes: [{
                stacked: orientation === "vertical",
                ticks: ticksX,
                scaleLabel: {
                    display: true,
                    labelString: xAxisTitle,
                    fontStyle: "bold",
                    fontSize: "11"
                }
            }]
        },
        tooltips: {
            enabled: orientation === "vertical",
            callbacks: {
                title: function(tooltipItems, data){
                    //console.log(tooltipItems);
                    //console.log(data);
                    return data.datasets[tooltipItems[0].datasetIndex].label;
                },
                label: function(tooltipItems, data) {
                    var numberFormat = new Intl.NumberFormat("es-CO");

                    var label = Liferay.Language.get('herramientapo.graphs-bar.tooltip-1') + ": ";
                    var value = round(tooltipItems.yLabel/1000000, 3);

                    if (orientation !== "vertical") {
                        label = Liferay.Language.get('herramientapo.graphs-bar.tooltip-2') + ": ";
                        value = round(tooltipItems.xLabel, 0);
                    }

                    label = " ";
                    label += "$" + numberFormat.format(value) + "(Millones)";
                    return label;
                }
            },
            bodyFontSize: (window.innerWidth < 600) ? 12:13
        },
        events: events,
        animation: {
            duration: 1,
            onComplete: function () {
                var chartInstance = this.chart,
                    ctx = chartInstance.ctx;

                var fontSize;
                if ($(window).innerWidth() >= 1024) {
                    //ctx.font = chartJS.Chart.helpers.fontString("14px", "bold", "open_sansregular");
                    fontSize = "14px ";
                } else {
                    //ctx.font = chartJS.Chart.helpers.fontString("12px", "bold", "open_sansregular");
                    fontSize = "12px ";
                }

                ctx.font = fontSize + "open_sansregular";

                if (orientation !== "vertical") {
                    this.data.datasets.forEach(function (dataset, i) {
                        var meta = chartInstance.controller.getDatasetMeta(i);
                        meta.data.forEach(function (bar, index) {
                            var numberFormat = new Intl.NumberFormat("es-CO");

                            var data = "$" + numberFormat.format(round(dataset.data[index], 0));
                            var width;


                            if ($(window).innerWidth() < 600) {
                                //ctx.font = chartJS.Chart.helpers.fontString("12px", "bold", "open_sansregular");
                                ctx.font = "12px open_sansregular";
                            } else {
                                //ctx.font = chartJS.Chart.helpers.fontString("14px", "bold", "open_sansregular");
                                ctx.font = "14px open_sansregular";
                            }

                            width = ctx.measureText(data).width;

                            ctx.strokeStyle = "rgb(255, 0, 0)";
                            ctx.fillStyle = "rgba(0, 0, 0, 0.8)";
                            roundRect(ctx, bar._model.x - offsetX, bar._model.y - offsetY-16, width+16, 32, 10, true, false);


                            ctx.textBaseline = 'middle';
                            ctx.fillStyle = '#fff';
                            ctx.fillText(data, bar._model.x - offsetX + 8, bar._model.y - offsetY);

                        });
                    });
                }

                else {
                    this.data.datasets.forEach(function (dataset, i) {
                        var tmpMeta = chartInstance.controller.getDatasetMeta(1);
                        var meta = chartInstance.controller.getDatasetMeta(i);
                        meta.data.forEach(function (bar, index) {
                            var numberFormat = new Intl.NumberFormat("es-CO");

                            var data;
                            var barHeight;
                            var percentValue = dataset.data[index] / originalData[index];
                            var percentText = round(percentValue * 100, 0) + "%";


                            ctx.font = "normal " + fontSize + "open_sansregular";
                            var width = ctx.measureText(percentText).width;

                            barHeight = bar._yScale.height - tmpMeta.data[index]._model.y;
                            //barHeight = 100;

                            ctx.textAlign = "center";
                            ctx.strokeStyle = "rgb(255, 0, 0)";
                            ctx.fillStyle = "rgba(200, 200, 200, 0.8)";
                            //console.log(barHeight);
                            //console.log(bar);
                            roundRect(ctx, bar._model.x - offsetX - (width / 2), bar._model.y + (barHeight*percentValue/2 - 8), width+16, 24, 10, true, false);

                            ctx.textBaseline = 'middle';
                            ctx.fillStyle = '#111';
                            ctx.fillText(percentText, bar._model.x - offsetX + 8, bar._model.y + (barHeight*percentValue/2 - 8) + 12);

                            if (i !== 0  && (window.innerWidth >= 600)) {
                                ctx.font = "bold " + fontSize + " open_sansregular";
                                data = "$" + numberFormat.format(round(originalData[index]/1000000, 3));
                                width = ctx.measureText(data).width;

                                ctx.textAlign = "center";
                                ctx.strokeStyle = "rgb(255, 0, 0)";
                                ctx.fillStyle = "rgba(0, 0, 0, 0.8)";
                                roundRect(ctx, bar._model.x - offsetX - (width / 2), bar._model.y - offsetY, width+16, 24, 10, true, false);

                                ctx.textBaseline = 'middle';
                                ctx.fillStyle = '#fff';
                                ctx.fillText(data, bar._model.x - offsetX + 8, bar._model.y - offsetY + 12);
                            }
                        });
                    });
                }

            }
        },
        legend: false,
        legendCallback: function(chart) {
            var legendHtml = [];
            if (orientation !== "vertical") {
                legendHtml.push("<ul class='labels-four'>");
            } else {
                legendHtml.push("<ul class='labels-five'>");
            }

            var item = (orientation === "vertical")? chart.data.datasets[1] : chart.data.datasets[0];
            for (var i=0; i < item.data.length; i++) {
                legendHtml.push("<li>");
                legendHtml.push('<span class="chart-legend" style="background-color:' + item.backgroundColor[i] +'"></span>');
                legendHtml.push('<span class="chart-legend-label-text">' + chart.data.labels[i]+'</span>');
                legendHtml.push("</li>");
            }

            legendHtml.push("</ul>");
            return legendHtml.join("");
        }
    };

    var myChart = new chartJS.Chart(ctx, {
        type: type,
        data: data,
        options: chartOptions
    });

    //console.log(maxValues);

    updateYAxes(myChart, orientation, maxValues);

    $("#legend_container").html(myChart.generateLegend());
}

function updateYAxes(myChart, orientation, maxValue) {
    //console.log("updating chart");
    if (orientation === "vertical") {
        myChart.options.scales.yAxes[0].ticks.max = maxValue[0] + (maxValue[0] - maxValue[1]);
        myChart.update();
    }
}

/**
 * Function to be called when tab 1 is selected.
 * Triggers drawing of line chart
 */
function onLineChartButtonClicked() {
    tabSelected = 3;

    /* Update title */
    var message = Liferay.Language.get('herramientapo.graphs-1.title');
    $("#title_graph").text("3. " + message);

    var labels = [];
    $.each(graphsData, function (key, value) {
        if (key === "dates") {
            labels = value;
        }
    });

    drawLineChart(graphsData, labels, portfolioName, portfolioData);
}

/**
 * Function to be called when tab 2 is selected.
 * Triggers drawing of vertical bar chart
 */
function onMaxChartButtonClicked() {
    tabSelected = 2;

    /* Update title */
    var message = Liferay.Language.get('herramientapo.graphs-2.title');
    $("#title_graph").text("2. " + message);

    var labels = [];
    var data = [];
    var labelSet;
    var labelAddedString = " Mayor Riesgo";

    /* Loop through data to obtain Labels and Data for Bars graph */
    $.each(graphsData, function (key, value) {
        if (key !== "dates") {
            labelSet = false;

            //console.log(key.toLowerCase());

            // Sort labels so that is organised as follows: "Colfondos - Porvenir - Protección - Old Mutual - Antiguo"
            for (var i = 0; i < brands.length; i++) {
                if (key.toLowerCase() === brands[i]) {
                    if (key === "Proteccion") {
                        labels[i] = "Protección" + labelAddedString;
                    } else {
                        labels[i] = key + labelAddedString;
                    }
                    data[i] = value[value.length-1];
                    labelSet = true;
                }
            }

            //
            if (!labelSet) {
                labels[brands.length] = portfolioName;
                data[brands.length] = portfolioData[portfolioData.length-1];
            }
        }
    });

    //console.log(labels);
    //console.log(data);

    drawBarChart(data, labels, "vertical", graphsData.contributions[0]);
}

/**
 * Function to be called when tab 3 is selected.
 * Triggers drawing of horizontal bar chart
 */
function  onTimeChartButtonClicked() {
    tabSelected = 1;

    /* Update title */
    var message = Liferay.Language.get('herramientapo.graphs-3.title');
    $("#title_graph").text("1. " + message);

    var labels = [];
    var data = [];
    var labelSet;
    var monthsValues = graphsData.data_months;
    var labelAddedString = " Mayor Riesgo";

    /* Loop through data to obtain Labels and Data for Bars graph */
    $.each(graphsData, function (key) {

        if (key !== "dates" && key !== "decision_months") {
            labelSet = false;

            // Sort labels so that is organised as follows: "Porvenir - Protección - Old Mutual - Antiguo"
            for (var i = 1; i < brands.length; i++) {
                if (key.toLowerCase() === brands[i]) {
                    if (key === "Proteccion") {
                        labels[i-1] = "Protección" + labelAddedString;
                    } else {
                        labels[i-1] = key + labelAddedString;
                    }
                    data[i-1] = monthsValues[i-1];    // Substract Colfondos value from others AFP
                    labelSet = true;
                }
            }

            // Portfolio label and data
            if (!labelSet) {
                labels[brands.length - 1] = portfolioName;
                data[brands.length - 1] = monthsValues[brands.length - 1];
            }
        }
    });

    //console.log(labels);
    //console.log(data);

    drawBarChart(data, labels, "horizontal");
}

/**
 * Function to reset canvas html element in order to
 * make it ready to render a new chart
 */
function resetCanvas() {
    $container = $("#canvas_container");
    $("#myChart").remove(); // this is my <canvas> element
    $container.append('<canvas id="myChart"><canvas>');
    canvas = document.querySelector("#myChart");
    ctx = canvas.getContext("2d");
    ctx.canvas.width = $container.width(); // resize to parent width
    ctx.canvas.height = $container.height(); // resize to parent height
}

function resetLegend() {
    $("#legend_container").empty();
}

/**
 * Function to round a decimal number
 * @param value: number to be rounded
 * @param decimals: amount of decimals in resultant number
 * @returns {number} rounded with decimals indicated
 */
function round(value, decimals) {
    return Number(Math.round(value+"e"+decimals)+"e-"+decimals);
}

function loadInitParams(resourceUrl) {
    if (hasInitParams) {
        return;
    }

    /* Require a resource to load init params from WS */
    $.ajax({
        type: "POST",
        dataType: "json",
        url: resourceUrl,
        success: function(data) {
            updateAges(data.male_age, data.female_age)
        }
    });

    hasInitParams = true;
}

function switchTabs(tab) {
    var $navTabs = $(".nav");

    //Deselect all tabs
    $navTabs.find("a").removeClass("active");

    var $list = $navTabs.children("li").eq(tab-1);

    //console.log($list);

    $list.find("a").addClass("active");
}

function updateAges(maleAge, femaleAge) {
    FEMALE_AGE = femaleAge;
    MALE_AGE = maleAge;

    maxPensionAge = FEMALE_AGE - 1;

    /* Set slider parameters */
    $("#age_slider").attr("max", FEMALE_AGE - 1);
    $("#max_slider").text((FEMALE_AGE - 1).toString());
}

/* Redraws charts on rezise */
$(window).on("resize", function () {
   if (isGraphPresent) {
       if (tabSelected === 1) {
           onTimeChartButtonClicked();
       } else if (tabSelected === 2) {
           onMaxChartButtonClicked();
       } else if (tabSelected === 3) {
           onLineChartButtonClicked();
       }
       switchTabs(tabSelected);
   }
});

/**
 * Draws a rounded rectangle using the current state of the canvas.
 * If you omit the last three params, it will draw a rectangle
 * outline with a 5 pixel border radius
 * @param {CanvasRenderingContext2D} ctx
 * @param {Number} x The top left x coordinate
 * @param {Number} y The top left y coordinate
 * @param {Number} width The width of the rectangle
 * @param {Number} height The height of the rectangle
 * @param {Number} [radius = 5] The corner radius; It can also be an object
 *                 to specify different radii for corners
 * @param {Number} [radius.tl = 0] Top left
 * @param {Number} [radius.tr = 0] Top right
 * @param {Number} [radius.br = 0] Bottom right
 * @param {Number} [radius.bl = 0] Bottom left
 * @param {Boolean} [fill = false] Whether to fill the rectangle.
 * @param {Boolean} [stroke = true] Whether to stroke the rectangle.
 */
function roundRect(ctx, x, y, width, height, radius, fill, stroke) {
    if (typeof stroke === 'undefined') {
        stroke = true;
    }
    if (typeof radius === 'undefined') {
        radius = 5;
    }
    if (typeof radius === 'number') {
        radius = {tl: radius, tr: radius, br: radius, bl: radius};
    } else {
        var defaultRadius = {tl: 0, tr: 0, br: 0, bl: 0};
        for (var side in defaultRadius) {
            radius[side] = radius[side] || defaultRadius[side];
        }
    }
    ctx.beginPath();
    ctx.moveTo(x + radius.tl, y);
    ctx.lineTo(x + width - radius.tr, y);
    ctx.quadraticCurveTo(x + width, y, x + width, y + radius.tr);
    ctx.lineTo(x + width, y + height - radius.br);
    ctx.quadraticCurveTo(x + width, y + height, x + width - radius.br, y + height);
    ctx.lineTo(x + radius.bl, y + height);
    ctx.quadraticCurveTo(x, y + height, x, y + height - radius.bl);
    ctx.lineTo(x, y + radius.tl);
    ctx.quadraticCurveTo(x, y, x + radius.tl, y);
    ctx.closePath();
    if (fill) {
        ctx.fill();
    }
    if (stroke) {
        ctx.stroke();
    }

}