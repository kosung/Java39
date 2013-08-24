"use strict";
jBit('tr')
function jBit(value) {
	var element;

	if (value instanceof Element) { // ex) .dataRow
		element = value;
	} else if (typeof value == 'function') {
		value();
		return;
	} else if (value.indexOf('#') == 0) { // ex) #aaaa
		element = document.getElementById(value.substr(1));
	} else if (value.indexOf('<') == 0) { // ex) <tr>
		element = document.createElement(value.substr(1, value.length - 2));
	} else if (value.indexOf('.') == 0) { // ex) .dataRow
		element = document.getElementsByClassName(value.substr(1));
	} else { // ex) 태그이름을 찾기: tr
		element = document.getElementsByTagName(value);
	}
	
	element.css = function(styleName, value) {
		if (arguments.length == 1) {
			if (length in this) {
				if (this.length > 0) {
					return this[0].style[styleName];
				}
			} else if (this instanceof Node) {
				return this.style[styleName];
			}
			return '';
		} else {
			if (length in this) {
				for (var i = 0; i < this.length; i++) {
					this[i].style[styleName] = value;
				}
			} else if (this instanceof Node) {
				this.style[styleName] = value;
			}
			return this;
		}
	};
	
	element.val = function(value) {
		if (arguments.length == 0) {
			if (length in this) {
				if (this.length > 0) {
					return this[0].value;
				}
			} else if (this instanceof Node) {
				return this.value;
			}
			return '';
		} else {
			if (length in this) {
				for (var i = 0; i < this.length; i++) {
					this[i].value = value;
				}
			} else if (this instanceof Node) {
				this.value = value;
			}
			return this;
		}
	};
	
	element.attr = function(name) {
		if (length in this) {
			if (this.length > 0) {
				return this[0].getAttribute(name);
			}
		} else if (this instanceof Element) {
			return this.getAttribute(name);
		}
		return '';
	};
	
	element.click = function(listener) {
		if (length in this) {
			for (var i = 0; i < this.length; i++) {
				this[i].onclick = listener;
			}
		} else if (this instanceof Node) {
			this.onclick = listener;
		}
		return this;
	};
	
	element.addClass = function(value) {
		if (length in this) {
			for (var i = 0; i < this.length; i++) {
				this[i].setAttribute('class', value);
			}
		} else if (this instanceof Node) {
			this.setAttribute('class', value);
		}
		return this;
	};
	
	element.html = function(content) {
		if (arguments.length == 0) {
			if (length in this && this.length > 0) {
				return this[0].innerHTML;
			} else if (this instanceof Node) {
				return this.innerHTML;
			} else {
				return '';
			}
		} else {
			if (length in this) {
				for (var i = 0; i < this.length; i++) {
					this[i].innerHTML = content;
				}
			} else if (this instanceof Node) {
				this.innerHTML = content;
			}
			return this;
		}
	};
	
	element.append = function(child) {
		if (length in this) {
			this[0].appendChild(child);
		} else if (this instanceof Node) {
			this.appendChild(child);
		}
		return this;
	};
	
	element.remove = function() {
		if (length in this) {
			for (var i = this.length - 1; i >= 0; i--) {
				this[i].parentNode.removeChild( this[i] );
			}
		} else if (this instanceof Node) {
			this.parentNode.removeChild( this );
		}
		return this;
	};
	
	element.appendTo = function(parent) {
		if (length in this) {
			for (var i = 0; i < this.length; i++) {
				parent.appendChild(this[i]);
			}
		} else if (this instanceof Node) {
			parent.appendChild(this);
		}
		
		return this;
	};
	
	return element;
}

var $ = jBit;

jBit.createXHR = function() {
	try {
        return new XMLHttpRequest();
    } catch (exception) {
        try {
            return new ActiveXObject('Msxml2.XMLHTTP');
        } catch (innerException) {
            return new ActiveXObject('Microsoft.XMLHTTP');
        }
    }
};

// settings
/*
 * type : GET or POST
 * dataType: 결과 데이터의 포맷. xml, json, text
 * url : 요청 URL
 * success: 성공했을 때 호출될 함수
 * error: 호출했을 때 호출될 함수
 */
jBit.ajax = function(settings) {
	var xhr = jBit.createXHR();
	/* 요청상태변경 
	 * 1. 요청 객체를 만들었음. open() 메서드 호출되었음.
	 * 2. send() 메서드를 호출했음.
	 * 3. 서버에서 데이터를 받고 있는 중임.
	 * 4. 서버에서 데이터를 모두 받았음.
	 */
	xhr.onreadystatechange = function(){
		if (xhr.readyState == 4) { // 서버에서 데이터를 모두 받았다면,
			if (xhr.status == 200) { // 서버에서 정상적으로 실행되었다면, 
				if (settings.dataType == 'json') {
					//var result = eval('(' + xhr.responseText + ')');
					var result = JSON.parse(xhr.responseText);
					settings.success(result);
					
				} else if (settings.dataType == 'xml') {
					settings.success(xhr.responseXML);
					
				}else {
					settings.success(xhr.responseText);
				}
			} else {
				settings.error('서버 요청이 실패했습니다!');
			}
		}
	};
	
	xhr.open(settings.type, settings.url, true);
	
	if (settings.type == 'POST') {
		xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		var params = '';
		for (var name in settings.data) {
			if (params != '') {
				params += "&";
			}
			params += name + "=" + encodeURIComponent(settings.data[name]);
		}
		xhr.send(params);
	
	} else {
		xhr.send();
	}
};

jBit.each = function(arr, func) {
	for(var i = 0; i < arr.length; i++) {
		func(i, arr[i]);
	}
};
























