/*global requirejs */

// Ensure any request for this webjar brings in dependencies. For example if this webjar contains
// bootstrap.js which depends on jQuery then you would have the following configuration.
//
requirejs.config({
  shim: {
    'jquery-cookie': [ 'webjars!jquery.js' ]
  }
});
