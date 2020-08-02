
// 将时间戳转换为可读字符串 （公共方法）
function timestampToDate(timestamp) {
    if ((typeof timestamp)  == "string") {
        timestamp = parseInt(timestamp.trim());
    }
    var date = new Date(timestamp);
    return dateToString(date);
}
// 将时间对象转换为可读字符串
function dateToString(date) {
    var y = date.getFullYear();
    var m = date.getMonth()+1;
    var d = date.getDate();
    var h = date.getHours();
    var mm = date.getMinutes();
    var s = date.getSeconds();
    return y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s);
}
function add0(m){return m<10?'0'+m:m }