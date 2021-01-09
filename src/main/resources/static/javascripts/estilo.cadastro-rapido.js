var Brewer = Brewer || {};

Brewer.EstiloCadastroRapido = (function () {
    function EstiloCadastroRapido() { //constructor
        this.modal = $('#modalCadastroRapidoEstilo');
        this.botaoSalvar = this.modal.find('.js-modal-cadastro-estilo-salvar-btn');
        this.form = this.modal.find('form');
        this.url = this.form.attr('action'); //pega o atributo action do form
        this.inputNomeEstilo = $('#nomeEstilo');
        this.containerMensagemErro = $('.js-mensagem-cadastro-rapido-estilo');
    }

    EstiloCadastroRapido.prototype.iniciar =function() {
        this.form.on('submit', function(event){event.preventDefault()}); // para de submeter o formulário
        this.modal.on('shown.bs.modal', onModalShow.bind(this));//evento depois que o modal é totalmente carregado. '.bind(this)' força a ser executado o método no contexto do objeto.
        this.modal.on('hide.bs.modal', onModalHide.bind(this));//evento depois que o modal é fechado
        this.botaoSalvar.on('click',onBotaoSalvarClick.bind(this));
    }

    function onModalShow() {
        this.inputNomeEstilo.focus();
    }

    function onModalHide() {
        this.inputNomeEstilo.val('');
        this.containerMensagemErro.addClass('hidden');
        this.form.find('.form-group').removeClass('has-error');
    }

    function onBotaoSalvarClick() {
        var nomeEstilo = this.inputNomeEstilo.val().trim(); //trim tira os espaços
        $.ajax({//requisição AJAX que envia para o servidor um JSON
            url: this.url,
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({nome: nomeEstilo}),
            error: onErroSalvandoEstilo.bind(this),
            success: onEstiloSalvo.bind(this)
        });
    }
    
    function onErroSalvandoEstilo(obj) {
        var mensagemErro =obj.responseText; //retorna a mensagem de erro da exception
        this.containerMensagemErro.removeClass('hidden');
        this.containerMensagemErro.html('<span>' + mensagemErro + '</span>');
        this.form.find('.form-group').addClass('has-error');
    }
    
    function onEstiloSalvo(estilo){
        var comboEstilo = $('#estilo');
        comboEstilo.append('<option value=' + estilo.codigo + '>' +estilo.nome + '</option>'); //adiciona o novo valor à lista
        comboEstilo.val(estilo.codigo); //seleciona o novo estilo
        this.modal.modal('hide');
    }

    return EstiloCadastroRapido;

}());

$(function() {
    var estiloCadastroRapido = new Brewer.EstiloCadastroRapido();
    estiloCadastroRapido.iniciar();
});