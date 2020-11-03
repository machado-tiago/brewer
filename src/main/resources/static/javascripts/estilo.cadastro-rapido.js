$(
    function(){
        var modal = $('#modalCadastroRapidoEstilo');
        var botaoSalvar = modal.find('.js-modal-cadastro-estilo-salvar-btn');
        var form = modal.find('form');
        form.on('submit', function(event){event.preventDefault()}); // para de submeter o formulário
        var url = form.attr('action'); //pega o atributo action do form
        var inputNomeEstilo = $('#nomeEstilo');
        var containerMensagemErro = $('.js-mensagem-cadastro-rapido-estilo');

        modal.on('shown.bs.modal', onModalShow);//evento depois que o modal é totalmente carregado
        modal.on('hide.bs.modal', onModalHide);//evento depois que o modal é fechado
        botaoSalvar.on('click',onBotaoSalvarClick);

        function onModalShow() {
            inputNomeEstilo.focus();
        }

        function onModalHide() {
            inputNomeEstilo.val('');
            containerMensagemErro.addClass('hidden');
            form.find('.form-group').removeClass('has-error');
        }

        function onBotaoSalvarClick() {
            var nomeEstilo = inputNomeEstilo.val().trim(); //trim tira os espaços
            $.ajax({//requisição AJAX que envia para o servidor um JSON
                url: url,
                method: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({nome: nomeEstilo}),
                error: onErroSalvandoEstilo,
                success: onEstiloSalvo
            });
        }

        function onErroSalvandoEstilo(obj) {
            var mensagemErro =obj.responseText;
            containerMensagemErro.removeClass('hidden');
            containerMensagemErro.html('<span>' + mensagemErro + '</span>');
            form.find('.form-group').addClass('has-error');
        }

        function onEstiloSalvo(estilo){
            var comboEstilo = $('#estilo');
            comboEstilo.append('<option value=' + estilo.codigo + '>' +estilo.nome + '</option>'); //adiciona o novo valor à lista
            comboEstilo.val(estilo.codigo); //seleciona o novo estilo
            modal.modal('hide');
        }
    }
);