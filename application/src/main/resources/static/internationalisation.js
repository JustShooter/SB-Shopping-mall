document.addEventListener("DOMContentLoaded", function () {

    const pageSizeSelect = document.getElementById("pageSizeSelect");


    pageSizeSelect.addEventListener("change", function () {
        let uri = window.location.href;
        const pageSize = pageSizeSelect.value;
        if (uri.includes("?")) {
            const attributes = uri.split("?")[1].split("&");
            const index = attributes.findIndex(x => x.includes("size"));
            if (index > -1) {
                attributes[index] = "size=" + pageSize;
            } else {
                attributes.push("size=" + pageSize);
            }
            window.location.href = uri.split("?")[0] + "?" + attributes.join("&");
        } else {
            window.location.href = uri + "?size=" + pageSize;
        }
    });
    // get locale selectLocale element
    const selectLocale = document.getElementById("locales");
    // add event listener to change event of selectLocale element
    selectLocale.addEventListener("change", function () {
        let uri = window.location.href;
        // get selected locale
        const locale = selectLocale.value;
        //check if uri has attributes
        if (uri.indexOf("?") > -1) {
            // get attributes from uri
            const attributes = uri.split("?")[1].split("&");
            // check if locale is in attributes
            const index = attributes.findIndex(s => s.includes("locale="));
            if (index > -1) {
                // change locale in attributes
                attributes[index] = "locale=" + locale;
                // set uri from attributes
                uri = uri.split("?")[0] + "?" + attributes.join("&");
            } else {
                // add locale to index
                uri = uri + "&locale=" + locale;
            }
        } else {
            // set uri with locale
            uri = uri.split("?")[0] + "?locale=" + locale;
        }
        // set uri
        window.location.href = uri;
    });

});