$(function() {
    $("input").on("change", function() {
        var input = $(this);
        if (input.val().length) {
            input.addClass("populated");
            $(".sim-po .currency_input .icon-pesos").css("top", "-8px");
        } else {
            input.removeClass("populated");
        }
    });

    $("input").focusout(function () {
        var input = $(this);
        if (input.val().length) {
            input.addClass("populated");
        } else {
            input.removeClass("populated");
        }
    });
});
$(function() {
    $(".nav-link").on("click", function() {
        var item = $(this);
        if (
            $(".nav-link").hasClass("active")
        ) {
            $(".nav-link").removeClass("active");
            item.addClass("active");
        } else {
            item.addClass("active");
        }
    });
});
$(function() {
    $(".nav-link-mb").on("click", function() {
        var item = $(this);
        if ($(".nav-link-mb").hasClass("active")) {
            $(".nav-link-mb").removeClass("active");
            item.addClass("active");
        } else {
            item.addClass("active");
        }
    });
});

function onGenderButtonClicked(id) {
    var $text = $("#tableTextId");

    var ageValue = $("#age_input").val();

    if (id === "male_button") {
        /* Set max pension age */
        maxPensionAge = MALE_AGE - 1;

        /* Change button images */
        $("#male_img").attr("src", MODULE_PATH + "/assets/icono_hombre_activo.png");
        $("#female_img").attr("src", MODULE_PATH + "/assets/icono_mujer.png");

        /* Set input value */
        $text.attr("value", "hombre");

        /* Set slider parameters */
        $("#age_slider").attr("max", MALE_AGE - 1);
        $("#max_slider").text((MALE_AGE - 1).toString());
    } else {
        /* Set max pension age */
        maxPensionAge = FEMALE_AGE - 1;

        /* Change input value if necessary */
        if (ageValue > maxPensionAge) {
            $("#age_input").val(maxPensionAge);
        }

        /* Change button images */
        $("#female_img").attr("src", MODULE_PATH + "/assets/icono_mujer_activa.png");
        $("#male_img").attr("src", MODULE_PATH + "/assets/icono_hombre.png");

        /* Set input value */
        $text.attr("value", "mujer");

        /* Set slider parameters */
        $("#age_slider").attr("max", FEMALE_AGE - 1);
        $("#max_slider").text((FEMALE_AGE - 1).toString());
    }
}

function onAFPClicked(id) {
    var $afpName = $("#afp_input");

    var $imgColfondos = $("#img_colfondos");
    var $imgPorvenir = $("#img_porvenir");
    var $imgProteccion = $("#img_proteccion");
    var $imgOldM = $("#img_old_m");

    afpSelected = true;
    $(".types").css({ "border": "none", "padding": "0", "border-radius": "5px" });
    $("#error_afp").css({ "visibility": "hidden" });

    switch (id) {
        case "img_colfondos":
            $afpName.attr('value', "Colfondos");
            $imgColfondos.attr('src', MODULE_PATH + '/assets/colfondos.png');
            $imgPorvenir.attr('src', MODULE_PATH + '/assets/porvenir_bn.png');
            $imgProteccion.attr('src', MODULE_PATH + '/assets/proteccion_bn.png');
            $imgOldM.attr('src', MODULE_PATH + '/assets/old_mutual_bn.png');
            break;
        case "img_porvenir":
            $afpName.attr('value', "Porvenir");
            $imgColfondos.attr('src', MODULE_PATH + '/assets/colfondos_bn.png');
            $imgPorvenir.attr('src', MODULE_PATH + '/assets/porvenir.png');
            $imgProteccion.attr('src', MODULE_PATH + '/assets/proteccion_bn.png');
            $imgOldM.attr('src', MODULE_PATH + '/assets/old_mutual_bn.png');
            break;
        case "img_proteccion":
            $afpName.attr('value', "Proteccion");
            $imgColfondos.attr('src', MODULE_PATH + '/assets/colfondos_bn.png');
            $imgPorvenir.attr('src', MODULE_PATH + '/assets/porvenir_bn.png');
            $imgProteccion.attr('src', MODULE_PATH + '/assets/proteccion.png');
            $imgOldM.attr('src', MODULE_PATH + '/assets/old_mutual_bn.png');
            break;
        case "img_old_m":
            $afpName.attr('value', "Old Mutual");
            $imgColfondos.attr('src', MODULE_PATH +'/assets/colfondos_bn.png');
            $imgPorvenir.attr('src', MODULE_PATH + '/assets/porvenir_bn.png');
            $imgProteccion.attr('src', MODULE_PATH + '/assets/proteccion_bn.png');
            $imgOldM.attr('src', MODULE_PATH + '/assets/old_mutual.png');
            break;
    }
}

function validateForm() {
    /* AFP name was not selected */
    if (!afpSelected) {
        $(".types").css({ "border": "1px solid red", "padding": "4px", "border-radius": "5px" });
        $("#error_afp").css({ "visibility": "visible" });
    } else {
        $(".form-container").find('[type="submit"]').trigger('click');
    }
}