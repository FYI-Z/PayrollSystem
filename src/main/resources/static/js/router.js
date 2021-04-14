var path = window.location.host;

function getPath(host) {
    var path = "";
    if (host.indexOf('localhost') >= 0 || host.indexOf('127.0.0.1') >= 0) {
        path = "http://localhost:8888";
        return path;
    }
    return path;
}