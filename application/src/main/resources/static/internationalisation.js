document.addEventListener("DOMContentLoaded", function () {

    // get locale select element
    var select = document.getElementById("locales");
    // add event listener to change event of select element
    select.addEventListener("change", function () {
        // get uri
        var uri = window.location.href;
        // get selected locale
        var locale = select.value;
        //check if uri has attributes
        if (uri.indexOf("?") > -1) {
            // get attributes from uri
            var attributes = uri.split("?")[1].split("&");
            // loop through attributes
            for (var i = 0; i < attributes.length; i++) {
                // get attribute
                var attribute = attributes[i];
                // get key and value
                var key = attribute.split("=")[0];
                var value = attribute.split("=")[1];
                // check if key is locale
                if (key == "locale") {
                    // set value to locale
                    value = locale;
                }
                // set uri
                uri = uri.split("?")[0] + "?" + key + "=" + value;
            }
        } else {
            // set uri
            uri = uri.split("?")[0] + "?locale=" + locale;
        }
        // set uri
        window.location.href = uri;
    });
});