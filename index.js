function loadPage(url) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4) {
            if (this.status === 200) {
                document.getElementById("content").innerHTML = this.responseText;
            } else {
                document.getElementById("content").innerHTML = "Failed to load content.";
            }
        }
    };
    xhttp.open('GET', url, true);
    xhttp.send();
}

function loadPage1(url) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4) {
            if (this.status === 200) {
                document.getElementById("poem-container").innerHTML = this.responseText;
            } else {
                document.getElementById("poem-container").innerHTML = "Failed to load content.";
            }
        }
    };
    xhttp.open('GET', url, true);
    xhttp.send();
}




