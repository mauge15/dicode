    (function($){  
    $.extend(  
    {  
        jsonp: {  
            script: null,  
            options: {},  
            call: function(url, options) {  
                var default_options = {  
                    callback: function(){},  
                    callbackParamName: "callback",  
                    params: []  
                };  
                this.options = $.extend(default_options, options);  
                //Determina si se debe añadir el parámetro separado por ? o por &  
                var separator = url.indexOf("?") > -1? "&" : "?";  
                var head = $("head")[0];  
                /*Serializa el objeto en una cadena de texto con formato URL*/  
                var params = [];  
                for(var prop in this.options.params){  
                    params.push(prop + "=" + encodeURIComponent(options.params[prop]));  
                }  
                var stringParams = params.join("&");  
                //Crea el script o borra el usado anteriormente  
                if(this.script){  
                    head.removeChild(script);  
                }  
                script = document.createElement("script");  
                script.type = "text/javascript";  
                //Añade y carga el script, indicandole que llame al metodo process  
                script.src = url + separator + stringParams + (stringParams?"&":"") + this.options.callbackParamName +"=jQuery.jsonp.process";  
                head.appendChild(script);  
            },  
            process: function(data) {this.options.callback(data);}  
        }  
    });  
    })(jQuery);
    
    function copeItCall(operation, code){  
        //var url = "http://copeit.cti.gr/user";
        var url = "http://dicodedev.cti.gr/user"; 
        var params = {  
            callback: function(data){ alert(data);},  
            callbackParamName: "jsoncallback",  
            params: {  
                op: operation,  
                hashData: code  
            }  
        };  
        $.jsonp.call(url, params);  
    }