var chartJS;

Liferay.Loader.require('chart-js', function (chartJs) {
    chartJS = chartJs;
});

/**
 * Function to draw line graphs based on
 * @param data: containing colfondos, porvenir, proteccion, old_mutual
 * @param labels: dates to show in x axis
 */
function drawLineChart(data, period, afpSelected) {
    afpSelected = afpSelected || false;

    resetCanvas($("#line_chart"), "line_chart");

    chartJS.Chart.defaults.global.defaultFontFamily = "open_sansregular";
    chartJS.Chart.defaults.global.defaultFontSize = 14;

    var ctx;
    var $canvas = $("#line_chart");
    if( $canvas.length ) ctx = $canvas.get(0).getContext("2d");
    else console.log("Error: Canvas not found with selector #canvas");

    var dataColf = [salaryValue];
    var dataProt = [salaryValue];
    var dataPorv = [salaryValue];
    var dataOld = [salaryValue];
    var labels = [0];

    var tmpMonth = 6;
    for (var i = 1; ; ) {

        /* Store label value */
        labels.push(tmpMonth);

        /* Update month value */
        if (tmpMonth < 12) {
            /* Store data for every AFP */
            dataColf.push(data[i]["colfondos"]);
            dataProt.push(data[i]["proteccion"]);
            dataPorv.push(data[i]["porvenir"]);
            dataOld.push(data[i]["oldMutual"]);
            i++;
        } else {
            if (tmpMonth % 12 === 0) {
                /* Store data for every AFP */
                dataColf.push(data[i]["colfondos"]);
                dataProt.push(data[i]["proteccion"]);
                dataPorv.push(data[i]["porvenir"]);
                dataOld.push(data[i]["oldMutual"]);

                i++;
            } else {
                /* Store empty data */
                dataColf.push(NaN);
                dataProt.push(NaN);
                dataPorv.push(NaN);
                dataOld.push(NaN);
            }
        }

        if (tmpMonth === period) {
            break;
        }

        tmpMonth += 6;
    }

    var colfondosGraph = {
        label: "Colfondos",
        data: dataColf,
        lineTension: 0.2,
        fill: true,
        spanGaps: true,
        backgroundColor: "rgba(0, 160, 234,0.1)",
        borderColor: "rgba(0, 160, 234,1)",
        pointBorderColor: "white",
        pointBackgroundColor: "rgba(0, 160, 234,1)",
        pointRadius: 4,
        pointHoverRadius: 10
    };

    var porvenirGraph = {
        label: "Porvenir",
        data: dataPorv,
        lineTension: 0.2,
        fill: true,
        spanGaps: true,
        backgroundColor: "rgba(205, 110, 51,0.1)",
        borderColor: "rgba(205, 110, 51,1)",
        pointBorderColor: "white",
        pointBackgroundColor: "rgba(205, 110, 51,1)",
        pointRadius: 4,
        pointHoverRadius: 10
    };

    var proteccionGraph = {
        label: "Protección",
        data: dataProt,
        lineTension: 0.2,
        fill: true,
        spanGaps: true,
        backgroundColor: "rgba(239, 200, 70, 0.1)",
        borderColor: "rgba(239, 200, 70, 1)",
        pointBorderColor: "white",
        pointBackgroundColor: "rgba(239, 200, 70, 1)",
        pointRadius: 4,
        pointHoverRadius: 10
    };

    var oldMutualGraph = {
        label: "Old Mutual",
        data: dataOld,
        lineTension: 0.2,
        fill: true,
        spanGaps: true,
        backgroundColor: "rgba(0, 102, 53, 0.15)",
        borderColor: "rgba(0, 102, 53, 1)",
        pointBorderColor: "white",
        pointBackgroundColor: "rgba(0, 102, 53, 1)",
        pointRadius: 4,
        pointHoverRadius: 10
    };

    var graphData = {
        labels: labels,
        datasets: [
            colfondosGraph,
            proteccionGraph,
            porvenirGraph,
            oldMutualGraph
        ]
    };

    /* Maps to store data by AFP */
    var graphsMap = {
        proteccion: proteccionGraph,
        porvenir: porvenirGraph,
        oldMutual: oldMutualGraph
    };

    /* Select Data to plot when afp is selected */
    if (afpSelected) {
        graphData = {
            labels: labels,
            datasets: [
                colfondosGraph,
                graphsMap[afpSelected]
            ]
        };
    }


    var chartOptions = {
        responsive: true,
        maintainAspectRatio: false,
        scales: {
            yAxes: [{
                ticks: {
                    display: $(window).innerWidth() >= 600,
                    fontSize: 11,
                    fontStyle: "bold",
                    beginAtZero:false,
                    callback: function(value) {
                        var numberFormat = new Intl.NumberFormat("es-CO");
                        return "$" + numberFormat.format(value/1000000);
                    }
                },
                scaleLabel: {
                    display: true,
                    labelString: "Pesos\n\nMillones",
                    fontStyle: "bold",
                    fontSize: "11"
                }
            }],
            xAxes: [{
                ticks: {
                    fontSize: 11,
                    fontStyle: "bold"
                },
                scaleLabel: {
                    display: true,
                    labelString: "Meses",
                    fontStyle: "bold",
                    fontSize: "11"
                }
            }]
        },
        tooltips: {
            enabled: true,
            callbacks: {
                title: function(tooltipItems) {
                    return tooltipItems[0].xLabel + " Meses";
                },
                label: function(tooltipItems, data) {
                    var numberFormat = new Intl.NumberFormat("es-CO");

                    var label = data.datasets[tooltipItems.datasetIndex].label || "";

                    if (label) {
                        label += ": $";
                    }

                    label += numberFormat.format(round(tooltipItems.yLabel, 2));
                    return label;
                }
            }
        },
        legend: false,
        legendCallback: function(chart) {
            var legendHtml = [];
            legendHtml.push("<ul>");
            var item = chart.data.datasets;
            for (var i=0; i < item.length; i++) {
                legendHtml.push("<li>");
                legendHtml.push('<span class="chart-legend" style="background-color:' + item[i].pointBackgroundColor +'"></span>');
                legendHtml.push('<span class="chart-legend-label-text"><span>' + item[i].label + '</span><br>' + portfoliosNames[projectionValue][i] +'</span>');
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

    $("#legend_line_container").html(myChart.generateLegend());
}

/**
 * Function to draw horizontal or vertical bar chart depending on
 * @param originalData {json object}: data to plot
 * @param period {number}: period of month
 * @param afpSelected {string}:
 * @param gridOn {boolean}: indicates whether grid is active or not
 */
function drawBarChart(originalData, period, afpSelected, gridOn) {
    afpSelected = afpSelected || false;

    if (typeof gridOn === "undefined") {
        gridOn = true;
    }

    resetCanvas($("#bar_chart"), "bar_chart");

    var ctx;
    var $canvas = $("#bar_chart");
    if( $canvas.length ) {
        ctx = $canvas.get(0).getContext("2d");
        ctx.clearRect(0, 0, $canvas.width, $canvas.height);
    }
    else {
        console.log("Error: Canvas not found with selector #canvas");
        return;
    }

    var dataIndex = (period / 12) + 1;

    var originalDataBars = originalData["final_value"];
    var originalDataLines = originalData.rent;

    var barsData = [
        originalDataBars[dataIndex].colfondos,
        originalDataBars[dataIndex].proteccion,
        originalDataBars[dataIndex].porvenir,
        originalDataBars[dataIndex].oldMutual
    ];

    var linesData = [
        originalDataLines[dataIndex].colfondos,
        originalDataLines[dataIndex].proteccion,
        originalDataLines[dataIndex].porvenir,
        originalDataLines[dataIndex].oldMutual
    ];

    var labelsMap = {
        proteccion: "Protección",
        porvenir: "Porvenir",
        oldMutual: "Old Mutual"
    };

    var colorsMap = {
        proteccion: "rgba(239, 200, 70, 1)",
        porvenir: "rgba(205, 110, 51,1)",
        oldMutual: "rgba(0, 102, 53, 1)"
    };

    var labels = [
        "Colfondos",
        "Protección",
        "Porvenir",
        "Old Mutual"
    ];

    var type = "bar";
    var yAxisTitleBar = "Valor final en pesos\n(Millones)";
    var yAxisTitleLine = "Rentabilidad E.A.";
    var xAxisTitle = "AFPs";

    var backgroundColor = [
        "rgba(0, 160, 234,1)",
        "rgba(239, 200, 70, 1)",
        "rgba(205, 110, 51,1)",
        "rgba(0, 102, 53, 1)"
    ];

    var maxValues;
    var ticksYBar = {
        display: $(window).innerWidth() >= 600,
        fontSize: 11,
        fontStyle: "bold",
        beginAtZero:false,
        suggestedMin: salaryValue,
        callback: function(value, index, values) {
            maxValues = values;
            var numberFormat = new Intl.NumberFormat("es-CO");
            return "$" + numberFormat.format(value/1000000);
        }
    };

    var ticksYLine = {
        display: true,
        fontSize: 11,
        fontStyle: "bold",
        beginAtZero: true,
        callback: function (value, index, values) {
            return value + "%";
        }
    };

    var ticksX = {
        display: $(window).innerWidth() >= 950,
        fontSize: ($(window).innerWidth() >= 950) ? 10 : 11,
        fontStyle: "bold"
    };

    var offsetY = 24;
    var offsetX = 8;

    /* Select Data to plot when afp is selected */
    if (afpSelected) {
        barsData = [
            originalDataBars[dataIndex].colfondos,
            originalDataBars[dataIndex][afpSelected]
        ];

        linesData = [
            originalDataLines[dataIndex].colfondos,
            originalDataLines[dataIndex][afpSelected]
        ];

        labels = [
            "Colfondos",
            labelsMap[afpSelected]
        ];

        backgroundColor = [
            "rgba(0, 160, 234,1)",
            colorsMap[afpSelected]
        ]
    }


    var graphDataBar = {
        data: barsData,
        backgroundColor: backgroundColor,
        yAxisID: "y-axis-bar"
    };

    var graphDataLines = {
        type: 'line',
        data: linesData,
        fill: false,
        borderColor: "#333",
        borderDash: [10, 5],
        pointBorderColor: "white",
        pointBackgroundColor: "#333",
        pointRadius: 4,
        lineTension: 0.1,
        yAxisID: "y-axis-line"
    };

    var data = {
        labels: labels,
        datasets: [
            graphDataLines,
            graphDataBar
        ]
    };

    var chartOptions = {
        responsive: true,
        maintainAspectRatio: false,
        scales: {
            yAxes: [{
                id: "y-axis-bar",
                ticks: ticksYBar,
                gridLines: {
                    display: gridOn
                },
                scaleLabel: {
                    display: true,
                    labelString: yAxisTitleBar,
                    fontStyle: "bold",
                    fontSize: "11"
                }
            }, {
                id: "y-axis-line",
                position: "right",
                ticks: ticksYLine,
                gridLines: {
                    display: gridOn
                },
                scaleLabel: {
                    display: true,
                    labelString: yAxisTitleLine,
                    fontStyle: "bold",
                    fontSize: "11"
                }
            }],
            xAxes: [{
                ticks: ticksX,
                gridLines: {
                    display: false
                },
                scaleLabel: {
                    display: true,
                    labelString: xAxisTitle,
                    fontStyle: "bold",
                    fontSize: "11"
                }
            }]
        },
        tooltips: {
            filter: function (tooltipItems) {
                var dataset = tooltipItems.datasetIndex;
                if ($(window).innerWidth() < 480) {
                    return true;
                }
                if (dataset !== 0) {
                    return false;
                } else {
                    return true;
                }
            },
            enabled: true,//$(window).innerWidth() < 950,
            callbacks: {
                label: function(tooltipItems, data) {
                    var dataset = tooltipItems.datasetIndex;
                    var numberFormat = new Intl.NumberFormat("es-CO");

                    var label = "Valor final en pesos: ";
                    var value = round(tooltipItems.yLabel, 2);

                    if (dataset === 0) {        // Line tooltip
                        label = "Rentabilidad E.A: ";
                        label += value + "%";
                    } else {                    // Bar tooltip
                        label += "$" + numberFormat.format(value);
                    }

                    return label;
                }
            }
        },
        events: ["touchstart", "touchmove", "touchend", "click"],
        animation: {
            duration: 1,
            onComplete: function () {
                var chartInstance = this.chart,
                    ctx = chartInstance.ctx;

                if ($(window).innerWidth() >= 480) {
                    this.data.datasets.forEach(function (dataset, i) {
                        if (i == 0) {
                            return;
                        }
                        var meta = chartInstance.controller.getDatasetMeta(i);
                        meta.data.forEach(function (bar, index) {
                            var numberFormat = new Intl.NumberFormat("es-CO");

                            var data = "$" + numberFormat.format(round(dataset.data[index]/1000000, 1));

                            var width;

                            var barHeight = bar._yScale.height - bar._model.y;


                            if ($(window).innerWidth() < 600) {
                                //ctx.font = chartJS.Chart.helpers.fontString("12px", "bold", "open_sansregular");
                                ctx.font = "12px open_sansregular";
                            } else {
                                //ctx.font = chartJS.Chart.helpers.fontString("14px", "bold", "open_sansregular");
                                ctx.font = "14px open_sansregular";
                            }

                            width = ctx.measureText(data).width;

                            ctx.strokeStyle = "rgb(255, 0, 0)";
                            ctx.fillStyle = "rgba(200, 200, 200, 0.8)";
                            roundRect(ctx, bar._model.x - width/2 - offsetX, bar._model.y+ barHeight/2 -16, width+16, 32, 10, true, false);


                            ctx.textBaseline = 'middle';
                            ctx.fillStyle = '#111';
                            ctx.fillText(data, bar._model.x - width/2 - offsetX + 8, bar._model.y + barHeight/2 );
                        });
                    });
                }

            }
        },
        legend: false,
        legendCallback: function(chart) {
            var legendHtml = [];
            legendHtml.push("<ul>");
            var item = chart.data.datasets[1];
            for (var i=0; i < item.data.length; i++) {
                legendHtml.push("<li>");
                legendHtml.push('<span class="chart-legend" style="background-color:' + item.backgroundColor[i] +'"></span>');
                legendHtml.push('<span class="chart-legend-label-text"><span>' + chart.data.labels[i] + '</span><br>' + portfoliosNames[projectionValue][i] +'</span>');
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


    updateYAxes(myChart, maxValues);

    $("#legend_bar_container").html(myChart.generateLegend());
}

/**
 *
 * @param originalData {jsonData}
 * @param period {number}
 * @param single {boolean}
 * @param afpSelected {string}
 */
function drawScatterChart(originalData, period, single, afpSelected) {
    single = single || false;
    afpSelected = afpSelected || false;

    var ctx;
    var $canvas;

    var rentData = originalData["rent"];
    var volatilityData = originalData["volatility"];

    var dataIndex = (period / 12) - 1;

    var yAxisTitle = "Rentabilidad E.A.";
    var xAxisTitle = "Volatilidad";

    var xTicksOn = true;
    var gridsOn = true;

    var xPoints = {
        colfondos: volatilityData[dataIndex].colfondos,
        proteccion: volatilityData[dataIndex].proteccion,
        porvenir: volatilityData[dataIndex].porvenir,
        oldMutual: volatilityData[dataIndex].oldMutual,
    };

    var yPoints = {
        colfondos: rentData[dataIndex+2].colfondos,
        proteccion: rentData[dataIndex+2].proteccion,
        porvenir: rentData[dataIndex+2].porvenir,
        oldMutual: rentData[dataIndex+2].oldMutual,
    };


    if (single) {
        resetCanvas($("#single_chart"), "single_chart");

        var drawDownData = originalData["draw_down"];

        $canvas = $("#single_chart");

        yAxisTitle = "Draw Down";
        xAxisTitle = period + " Meses";

        xPoints = {
            colfondos: period,
            proteccion: period,
            porvenir: period,
            oldMutual: period
        };

        yPoints = {
            colfondos: drawDownData[dataIndex].colfondos,
            proteccion: drawDownData[dataIndex].proteccion,
            porvenir: drawDownData[dataIndex].porvenir,
            oldMutual: drawDownData[dataIndex].oldMutual
        };

        xTicksOn = false; // Deactivates legend in x axis
        gridsOn = false;

    } else {
        resetCanvas($("#scatter_chart"), "scatter_chart");

        $canvas = $("#scatter_chart");
    }

    if( $canvas.length ) {
        ctx = $canvas.get(0).getContext("2d");
        ctx.clearRect(0, 0, $canvas.width, $canvas.height);
    }
    else {
        console.log("Error: Canvas not found with selector #canvas");
        return;
    }

    var colfondosPoint = {
        label: "Colfondos",
        data: [{
            x: xPoints.colfondos,
            y: yPoints.colfondos
        }],
        pointBackgroundColor: "rgba(0, 160, 234,1)",
        pointRadius: 8,
        pointHoverRadius: 10
    };

    var proteccionPoint = {
        label: "Protección",
        data: [{
            x: xPoints.proteccion,
            y: yPoints.proteccion
        }],
        pointBackgroundColor: "rgba(239, 200, 70, 1)",
        pointRadius: 8,
        pointHoverRadius: 10
    };

    var porvenirPoint = {
        label: "Porvenir",
        data: [{
            x: xPoints.porvenir,
            y: yPoints.porvenir
        }],
        pointBackgroundColor: "rgba(205, 110, 51,1)",
        pointRadius: 8,
        pointHoverRadius: 10
    };

    var oldMutualPoint = {
        label: "Old Mutual",
        data: [{
            x: xPoints.oldMutual,
            y: yPoints.oldMutual
        }],
        pointBackgroundColor: "rgba(0, 102, 53, 1)",
        pointRadius: 8,
        pointHoverRadius: 10
    };

    /* Maps to store data by AFP */
    var pointsMap = {
        proteccion: proteccionPoint,
        porvenir: porvenirPoint,
        oldMutual: oldMutualPoint
    };

    var labelsMap = {
        proteccion: "Protección",
        porvenir: "Porvenir",
        oldMutual: "Old Mutual"
    };

    /* Data to plot*/
    var scatterData = {
        labels: [
            "Colfondos",
            "Protección",
            "Porvenir",
            "Old Mutual"
        ],
        datasets: [
            colfondosPoint,
            proteccionPoint,
            porvenirPoint,
            oldMutualPoint,
        ]
    };

    /* Select data when afp is selected */
    if (afpSelected) {
        scatterData = {
            labels: [
                "Colfondos",
                labelsMap[afpSelected]
            ],
            datasets: [
                colfondosPoint,
                pointsMap[afpSelected]
            ]
        };
    }

    var ticksY = {
        display: true,
        fontSize: 11,
        fontStyle: "bold",
        beginAtZero:true,
        callback: function(value, index, values) {
            return value + "%";
        }
    };

    var ticksX = {
        display: xTicksOn,
        fontSize: ($(window).innerWidth() >= 950) ? 10 : 11,
        fontStyle: "bold",
        callback: function(value, index, values) {
            var concatString = "%";
            return value + concatString;
        }
    };

    var chartOptions = {
        responsive: true,
        maintainAspectRatio: false,
        scales: {
            yAxes: [{
                ticks: ticksY,
                gridLines: {
                    display: gridsOn
                },
                scaleLabel: {
                    display: true,
                    labelString: yAxisTitle,
                    fontStyle: "bold",
                    fontSize: "11"
                }
            }],
            xAxes: [{
                ticks: ticksX,
                gridLines: {
                  display: gridsOn
                },
                scaleLabel: {
                    display: true,
                    labelString: xAxisTitle,
                    fontStyle: "bold",
                    fontSize: "11"
                }
            }]
        },
        tooltips: {
            enabled: true,
            callbacks: {
                title: function(tooltipItems, data) {
                    return data.labels[tooltipItems[0].datasetIndex];
                },
                label: function(tooltipItems, data) {
                    var label = [["Rentabilidad E.A.: " + tooltipItems.yLabel + "% "], ["Volatilidad: " + tooltipItems.xLabel + "%"]];

                    if (single) {
                        label = "Máxima pérdida: " + tooltipItems.yLabel + "%";
                    }

                    return label;
                }
            },
            displayColors: false
        },
        legend: false,
        legendCallback: function(chart) {
            var legendHtml = [];
            legendHtml.push("<ul>");
            var item = chart.data.datasets;
            for (var i=0; i < item.length; i++) {
                legendHtml.push("<li>");
                legendHtml.push('<span class="chart-legend" style="background-color:' + item[i].pointBackgroundColor +'"></span>');
                legendHtml.push('<span class="chart-legend-label-text"><span>' + item[i].label + '</span><br>' + portfoliosNames[projectionValue][i] +'</span>');
                legendHtml.push("</li>");
            }

            legendHtml.push("</ul>");
            return legendHtml.join("");
        }
    };

    var myChart = new chartJS.Chart(ctx, {
        type: "scatter",
        data: scatterData,
        options: chartOptions
    });

    if (single) {
        $("#legend_single_container").html(myChart.generateLegend());
    } else {
        $("#legend_scatter_container").html(myChart.generateLegend());
    }

}

function drawDoubleBarChart(afpOriginalData, $chart) {
    chartJS.Chart.defaults.global.defaultFontFamily = "open_sansregular";
    chartJS.Chart.defaults.global.defaultFontSize = 14;

    $chart = resetCanvas($chart, $chart.attr('id').toString());

    var ctx;
    var $canvas = $chart;
    if( $canvas.length ) {
        ctx = $canvas.get(0).getContext("2d");
        ctx.clearRect(0, 0, $canvas.width, $canvas.height);
    }
    else {
        console.log("Error: Canvas not found with selector #canvas");
        return;
    }

    var colfBarData = {
        label: "Colfondos",
        data: mixData.finalValues.colfondos,
        backgroundColor: "rgba(0, 160, 234,1)"
    };

    var afpBarData = {
        label: afpOriginalData.label,
        data: afpOriginalData.data,
        backgroundColor: afpOriginalData.color
    };

    var barsData = {
        labels: ["12", "24", "36", "48", "60"],
        datasets: [colfBarData, afpBarData]
    };


    var type = "bar";
    var yAxisTitle = "Valor final\n(Millones)";
    var xAxisTitle = "Meses";

    var maxValues = [];
    var ticksY = {
        display: true,
        fontSize: 11,
        fontStyle: "bold",
        beginAtZero:true,
        suggestedMin: 0,
        callback: function(value, index, values) {
            var numberFormat = new Intl.NumberFormat("es-CO");
            return "$" + numberFormat.format(value/1000000);
        }
    };

    var ticksX = {
        display: true,
        fontSize: 11,
        fontStyle: "bold"
    };

    var offsetY = 5;
    var offserX = 0;

    var chartOptions = {
        responsive: true,
        maintainAspectRatio: false,
        scales: {
            yAxes: [{
                ticks: ticksY,
                scaleLabel: {
                    display: true,
                    labelString: yAxisTitle,
                    fontStyle: "bold",
                    fontSize: "11"
                }
            }],
            xAxes: [{
                ticks: ticksX,
                barPercentage: 1,
                categoryPercentage: 0.6,
                gridLines: {
                    display: false
                },
                scaleLabel: {
                    display: true,
                    labelString: xAxisTitle,
                    fontStyle: "bold",
                    fontSize: "11"
                }
            }]
        },
        tooltips: {
            enabled: true,
            callbacks: {
                title: function(tooltipItem, data) {
                    return data.datasets[tooltipItem[0].datasetIndex].label;
                },
                label: function(tooltipItems, data) {
                    var numberFormat = new Intl.NumberFormat("es-CO");

                    var label = "Valor final: ";
                    var value = round(tooltipItems.yLabel, 0);

                    label += "$" + numberFormat.format(value);
                    return label;
                }
            }
        },
        //events: ["touchstart", "touchmove", "touchend", "click"],
        legend: false,
        legendCallback: function(chart) {
            var legendHtml = [];
            legendHtml.push("<ul>");
            var item = chart.data.datasets;
            for (var i=0; i < item.length; i++) {
                legendHtml.push("<li>");
                legendHtml.push('<span class="chart-legend" style="background-color:' + item[i].backgroundColor +'"></span>');
                legendHtml.push('<span class="chart-legend-label-text">' + item[i].label + '</span>');
                legendHtml.push("</li>");
            }

            legendHtml.push("</ul>");
            return legendHtml.join("");
        }
    };

    var myChart = new chartJS.Chart(ctx, {
        type: type,
        data: barsData,
        options: chartOptions
    });

    var $legendContainer = $chart.parents(".graphic").children(".legend-container-mix");
    $legendContainer.html(myChart.generateLegend());
}

function updateYAxes(myChart, maxValue) {
    myChart.options.scales.yAxes[0].ticks.max = maxValue[0] + (maxValue[0] - maxValue[1]);
    myChart.update();
}


/**
 * Function to reset canvas html element in order to
 * make it ready to render a new chart
 */
function resetCanvas($chart, id) {
    var $canvas_container = $chart.parent();

    $chart.remove(); // this is my <canvas> element
    $canvas_container.append('<canvas id="'+id+'"><canvas>');

    return $canvas_container.children("canvas");
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