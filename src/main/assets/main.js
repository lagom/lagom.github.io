jQuery.noConflict();
(function($) {
    $(function() {
        //init foundation
        $(document).foundation();
        //need to us hack device detection as modernizr doesn't detect ios background cover bug
        var iOS = navigator.userAgent.match(/iPhone|iPad|iPod/i);
        if(iOS){$("html").addClass('ios').removeClass('noios')}
        new Waypoint.Sticky({
            element: $('.social-bar')[0]
        });

        // Init docs version selector
        var versionSelector = $("#docs-version");
        if (versionSelector) {
            versionSelector.change(function() {
                var selectedVersion = $("option:selected", this);
                window.location.href = selectedVersion.val();
            });
        }

        // Pretty print
        window.prettyPrint && prettyPrint();
    });
})(jQuery);
