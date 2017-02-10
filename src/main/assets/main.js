jQuery.noConflict();
(function($) {

    function scrollTo(target) {
        $("html, body").animate({
            scrollTop: target.offset().top
        }, 1000);
    }

    function initScrollTo() {
        // Match all anchor links
        $("a[href^='#'").each(function() {
            var element = $(this);
            var anchorId = element.attr("href");
            var anchor = $(anchorId);
            if (anchor) {
                element.click(function() {
                    scrollTo(anchor);
                    // We're going to return false, which means the default action won't happen,
                    // which means the URL won't get updated to have the fragment. So we use
                    // history.pushState to update it.
                    if (window.history) {
                        window.history.pushState({}, window.title, anchorId)
                    }
                    return false;
                });
            }
        });
    }

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

        // Init get started page widgets
        initScrollTo();

        // Pretty print
        window.prettyPrint && prettyPrint();
    });


})(jQuery);
