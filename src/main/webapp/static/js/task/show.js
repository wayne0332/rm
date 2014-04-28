$(function () {
    var cal = $("#calendar").calendar({
        format: "yyyy-mm-dd",
        startMode: "day",
        multiSelect: false,
        locale : "zhCN",
        otherDays: true,
        getDates: function (data) {
        },
        click: function (d) {
        }
    });
})