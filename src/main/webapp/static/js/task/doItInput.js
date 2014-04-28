$("#taskDetail").on('click', function(){
    $.Dialog({
        shadow: false,
        overlay: false,
        draggable: true,
        flat: true,
        icon: '<span class="icon-comments-3"></span>',
        title: 'Draggable window',
        width: 500,
        padding: 10,
        content: 'This Window is draggable by caption.',
        onShow: function(){
            $.Dialog.title($("#taskTitle").html());
            $.Dialog.content($("#taskContent").html());
        }

    });
});