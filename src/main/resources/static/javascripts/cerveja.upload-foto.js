$(function(){
    nomeFoto = $('input[name=nomeFoto]').val()
    if (nomeFoto) {
        $('#target').attr('src', '/brewer/fotos/temp/' + nomeFoto);
        $('#divfoto').css('display','block');
        $('#upload-form').hide();
    }

    $('#upload-select').change(function () {//função para exibir a imagem carregada
        var file = $('#upload-select')[0].files[0]; //botão com os arquivos carregados            
        var fileReader = new FileReader();
        fileReader.onloadend = function (){
            $('#target').attr('src', fileReader.result);//quando o arquivo for carregado, define o atributo src do elemento #target
        }
        fileReader.readAsDataURL(file);//converte o binário para o navegador ler
        $('#divfoto').css('display','block');
    });
})

function uploadFile(){//botão de upload
    var file = $('#upload-select')[0].files[0]; //botão com os arquivos carregados
    var form = new FormData();//conjunto de pares chave/valor
    
    if (file){
        form.append("file", file, file.name);
        var settings ={
            url:'/brewer/fotos/fileupload',
            type: 'POST',
            enctype: 'multipart/form-data',
            allow:'*.(jpg|jpeg|png)',
            cache: false,
            contentType: false,
            processData: false,
            filelimit: 1,
            data: form,//quando é do tipo form-data é necessário esse atributo, não sendo um json
            success: function(objeto) {
                $('input[name=nomeFoto]').val(objeto.nome);
                $('input[name=fileType]').val(objeto.contentType);
                $('#upload-form').hide();
                //$('input[name=foto]').val(objeto.foto);//passa os bytes do arquivo
                alert('Upload realizado com sucesso!');
            }
        }
        $.ajax(settings).done(function(response){
            console.log(response);
        });  

    }
}

function removeFile() {
    $('#nomeFoto').val('');
    $('#fileType').val('');
    $('#upload-select').val('');
    $('#divfoto').css('display','none');
    $('#target').attr('src', '');
    $('#upload-form').show();
}