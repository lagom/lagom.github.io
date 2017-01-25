jQuery.noConflict();
(function($) {

    function scrollTo(target) {
        $("html, body").animate({
            scrollTop: target.offset().top
        }, 1000);
    }

    function getStartedHighlightLanguage(language) {
        $("[data-select-language]").each(function() {
            var element = $(this);
            var lang = element.data("select-language");
            var languageExtra = $("#select-language-" + lang + "-extra");
            if (lang === language) {
                element.removeClass("glass");
                languageExtra.show();
            } else {
                element.addClass("glass");
                languageExtra.hide();
            }
            getStartedHighlightBuildTool(lang, "none");
        });
    }

    function getStartedHighlightBuildTool(language, buildTool) {
        $("[data-select-" + language + "-build-tool]").each(function() {
            var element = $(this);
            var tool = element.data("select-" + language + "-build-tool");
            if (tool === buildTool) {
                element.removeClass("glass");
            } else {
                element.addClass("glass");
            }
        });
    }

    function getStartedClickJava() {
        getStartedHighlightLanguage("java");
    }

    function getStartedClickJavaSbt() {
        getStartedHighlightLanguage("java");
        getStartedHighlightBuildTool("java", "sbt");
        scrollTo($("#scroll-java-sbt"));
    }

    function getStartedClickJavaMaven() {
        getStartedHighlightLanguage("java");
        getStartedHighlightBuildTool("java", "maven");
        scrollTo($("#scroll-java-maven"));
    }

    function getStartedClickScala() {
        getStartedHighlightLanguage("scala");
        scrollTo($("#scroll-scala-sbt"));
    }

    function initGetStarted() {
        $("[data-select-language='java']").each(function() {
           $(this).click(function() {
               getStartedClickJava();
           });
        });
        $("[data-select-language='scala']").each(function() {
            $(this).click(function() {
                getStartedClickScala();
            });
        });
        $("[data-select-java-build-tool='sbt']").each(function() {
            $(this).click(function() {
                getStartedClickJavaSbt();
            });
        });
        $("[data-select-java-build-tool='maven']").each(function() {
            $(this).click(function() {
                getStartedClickJavaMaven();
            });
        });

        if ($("[data-select-language]")) {
            console.log(window.location.hash);
            switch (window.location.hash) {
                case "#java":
                    getStartedClickJava();
                    break;
                case "#java-sbt":
                    getStartedClickJavaSbt();
                    break;
                case "#java-maven":
                    getStartedClickJavaMaven();
                    break;
                case "#scala-sbt":
                    getStartedClickScala();
                    break;
            }
        }
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
        initGetStarted();

        // Pretty print
        window.prettyPrint && prettyPrint();
    });


})(jQuery);
