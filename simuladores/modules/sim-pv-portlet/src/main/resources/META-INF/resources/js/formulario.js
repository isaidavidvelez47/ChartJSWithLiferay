/*
 * Global variables
 */
var isSelectEmpty = true;
var isSelMonthEmpty = true;
var isSalaryEmpty = true;
var isDateEmpty = true;
var isAfpEmpty = true;

var salaryValue;
var projectionValue;
var monthPeriod;
var cutDate;
var afpName;
var cacheInput = null;
var cacheSalaryVal = null;
var timer = null;

$(function() {
    if (isApple()) {
        $("#salary_input").on("focus",function(e){
            cacheInput = e.target;
            cacheSalaryVal = salaryValue;
        });

        $("#salary_input").on("blur", function () {
            if (cacheSalaryVal !== salaryValue) {
                $(this).trigger("change");
            }
        });

        $(document).on('touchend',function(e){
            if(e.target.tagName!=='INPUT'){
                //alert("Test touch outside input");
                if(cacheInput!==null){
                    timer = setTimeout(function(){
                        cacheInput.blur();
                        cacheInput = null;
                        clearTimeout(timer);
                    },200);
                }
            }
        });
    }

    $(".form").on("change", "input", function() {
        var input = $(this);
        if (input.val().length) {
            input.addClass("populated");

            isSalaryEmpty = false;

        } else {
            input.removeClass("populated");

            isSalaryEmpty = true;
        }
    });

    // As other browsers already fire the change event,
    // only bind the listener for IE.
    if ( window.navigator.userAgent.indexOf('Trident') >= 0 ) {
        $("input").on("keyup", function (e) {
            if(e.which === 13) {
                //alert('You pressed enter!');
                $(this).trigger("change");
            }
        });

        $("#salary_input").on("focusout", function (e) {
            $(this).trigger("change");
        });
    }

    $("#salary_input").focusout(function () {
        var input = $(this);
        if (input.val().length) {
            input.addClass("populated");

            isSalaryEmpty = false;

        } else {
            input.removeClass("populated");

            isSalaryEmpty = true;
        }

        if (!salaryValue) {
            salaryValue = 0;
        }

        if (salaryValue === 0) {
            input.removeClass("populated");
        }

        var numberFormat = new Intl.NumberFormat("es-CO");

        // Format number to es-CO Locale
        $(this).val( function() {
            return ( salaryValue === 0 ) ? "" : numberFormat.format(salaryValue);
        } );
    });

    /* Parse input currencies to colombian money */
    $(".amount_input").on( "keyup", ":input", function( event ) {

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

        // Save number before format
        salaryValue = input;

        // Format number to es-CO Locale
        $this.val( function() {
            return ( input === 0 ) ? "" : numberFormat.format(input);
        } );

    } );

    /* Restricts amount input to 10 digits max. */
    $(".amount_input").on("keydown", ":input", function (event) {
        var originalInput = $(this).val();

        originalInput = originalInput.replace(/\./g, "");

        var concatValue = salaryValue ? salaryValue.toString().concat(event.key): 0;

        var keyCode = event.keyCode;

        // Allow left and right arrows to be pressed also delete and backspace and enter
        if (keyCode !== 37 && keyCode !== 39 &&
            keyCode !== 46 && keyCode !== 8 && keyCode !== 13) {
            if (concatValue.length > 10 ||  originalInput.length >= 10) {
                event.preventDefault();
            }
        }

    });

    /* Switch classes to tabs */
    $(".nav-link button").on("click", function() {
        $(".nav-link").removeClass("active");

        var $item = $(this).parents(".nav-link");
        $item.addClass("active");
    });

    /* Change event in select inputs */
    $("select").on("change", function() {
        var id = $(this).attr("id");
        var $span = $(this).next("span");

        /* Animate label of select elements*/
        if (!$span.hasClass("span-active")) {
            $span.addClass("span-active");
            $(this).css({ "color": "black", "font-family": "open_sansregular" })
        }

        /* Actions depending on changed select */
        if (id === "sel_projection") {
            projectionValue = $("#"+id+" option:selected").val();

            changeNormalTablesLabels();

            isSelectEmpty = false;
        } else if (id === "sel_months") {
            monthPeriod = parseInt($("#"+id+" option:selected").text());

            isSelMonthEmpty = false;

            $(".t-normal tbody tr td").removeClass("selected-cells");
            $(".t-normal tbody tr td:nth-child("+(monthPeriod/12 + 1)+")").addClass("selected-cells");

            if (jsonData && isGraphVisible) {
                drawNormalGraphs();
            }
        } else if (id === "date_input") {
            cutDate = $("#"+id+" option:selected").val();
            isDateEmpty = false;
        } else if (id === "current_afp") {
            /* Check if none was selected */
            if ($(this).prop("selectedIndex") === 0) {
                isAfpEmpty = true;

                $span.removeClass("span-active");
                $(this).css({ "color": "#999", "font-family": "open_sanssemibold" });

                showRows();
            } else {
                isAfpEmpty = false;

                afpName = $("#"+id+" option:selected").val();

                hideRows(afpName);
            }

            if (jsonData && isGraphVisible) {
                drawNormalGraphs();
            }
        } else if (id === "current_afp_mix") {
            /* Check if none was selected */
            if ($(this).prop("selectedIndex") === 0) {
                isAfpEmpty = true;

                $span.removeClass("span-active");
                $(this).css({ "color": "#999", "font-family": "open_sanssemibold" });

                showAFPMixTables();
                showResumeGraphsAndTables();
            } else {
                isAfpEmpty = false;

                afpName = $("#"+id+" option:selected").val();

                hideMixTables(afpName);
                hideResumeGraphsAndTables(afpName);
            }
        }
    });

    /* Form normal submit */
    $("#form_normal").on("change", "input, select", function () {

        if (isTableVisible &&
            ($(this).attr("id") === "current_afp" || $(this).attr("id") === "sel_months")) {
            displayLoader();
            setTimeout(hideLoader, 300);
            return;
        }

        if (!isDateEmpty && !isSalaryEmpty && !isSelectEmpty && !isSelMonthEmpty) {
            if (salaryValue == 0 || typeof(salaryValue) === "undefined") return;

            var data = {
                salary: salaryValue,
                cutDate: cutDate,
                afp: projectionValue
            };

            // Display loader as data loads
            var $loadPageDiv = $("#load-page");

            $loadPageDiv.css("display", "block");

            sendData(data);
        } else {
            $("#tables_container").removeClass("visible");
        }
    });

    /* Form mix submit */
    $("#form_mix").on("change", "input, select", function () {
        if ($(this).attr("id") === "current_afp_mix") {
            displayLoader();
            setTimeout(hideLoader, 300);
            return;
        }

        if (!isDateEmpty && !isSalaryEmpty) {
            if (salaryValue == 0 || typeof(salaryValue) === "undefined") return;
            displayLoader();

            var data = {
                salary: salaryValue,
                cutDate: cutDate
            };

            sendData(data, "mix");
        } else {
            $("#tables_mix_container").removeClass("visible");
        }
    });

    /* Validates inputs of mix tables */
    $(".table").on("focus", "input", function () {
        var oldValue;

        if ($(this).val() == 0) {
            $(this).val("");
        }

        // Keydown
        $(this).on("keydown", function (event) {
            oldValue = $(this).val();  // Previous value in field

            var keyName = event.key;

            var concatValue = oldValue.concat(event.key);
            console.log(concatValue);

            // Prevents keys (-), (+), (.), (-), (,) from act in this input
            if (event.keyCode === 107 || event.keyCode === 109 || event.keyCode === 110 ||
                event.keyCode === 188 || event.keyCode === 189 || event.keyCode === 190 ||
                keyName == "Unidentified") {
                event.preventDefault();
            }

            // Allow left and right arrows to be pressed also delete and backspace and enter
            if (event.keyCode !== 37 && event.keyCode !== 39 &&
                event.keyCode !== 46 && event.keyCode !== 8 && event.keyCode !== 13) {
                if (concatValue.length === 3) {
                    var intValue = parseInt(concatValue);
                    if (intValue !== 100) {
                        //event.preventDefault();
                    }
                } else if (concatValue.length > 3) {
                    event.preventDefault();
                }
            }

        });

        $(this).on("keyup", function (event) {
            var value = $(this).val();
            if (value > 100) {
                $(this).val(oldValue);
            }
        });
    });

    // Workaround for android keycodes
    if (navigator.userAgent.toLowerCase().indexOf("android") > - 1) {
        $("table").on("textInput", "input", function(e) {
            var keyCode = e.originalEvent.data.charCodeAt(0);
            /*if (keyCode === 44 || keyCode === 45 || keyCode === 46) {
                e.preventDefault();
            }*/
            //Allowed keys
            var allowedKey = [30, 31, 32, 33, 34, 35, 36, 37, 38, 39];

            return ($.inArray(keyCode, allowedKey) !== -1);
        });
    }


    $(".table").on("focusout", "input", function () {
        var inputValue = $(this).val();

        if (!inputValue.length) {
            $(this).val(0);
            $(this).trigger("change");
        }
    });

});

function refreshForm() {
    $("select").prop("selectedIndex", 0);
    $(".form input").val("");
}

function displayLoader() {
    // Display loader as data loads
    var $loadPageDiv = $("#load-page");

    $loadPageDiv.css("display", "block");
}

function hideLoader() {
    // Hide loader
    var $loadPageDiv = $("#load-page");

    $loadPageDiv.css("display", "none");
}

function isApple(){
    var ua = navigator.userAgent.toUpperCase();
    var
        ipad = ua.indexOf('IPAD')>-1,
        ipod = ua.indexOf('IPOD')>-1,
        iphone = ua.indexOf('IPHONE')>-1 ;
    return   ipad || ipod || iphone ;
}
