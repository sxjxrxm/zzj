$(function() {
    window.onload = function() {
        var r = document.getElementById("statusSelect").children;
        var s = document.getElementById("sel");
        r[0].onclick = function() {
            s.disabled = this.checked;
        }
        r[1].onclick = function() {
            s.disabled = !this.checked;
        }
    }
})