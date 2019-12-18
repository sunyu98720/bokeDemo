var CommonAjax = function(InterfaceUrl,Type,Data,DataType){
    $.ajax({
        url:"http://localhost:8086" + InterfaceUrl,
        type:Type,
        data:Data,
        contentType : "application/json",
        dataType:DataType,
        async:false,
        success:function (data) {
            return data;
        },
        error:function (data_error) {
            return data_error
        }
    });

}