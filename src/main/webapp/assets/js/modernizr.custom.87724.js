/* Modernizr 2.6.2 (Custom Build) | MIT & BSD
 * Build: http://modernizr.com/download/#-backgroundsize-multiplebgs-csstransitions-svg-printshiv-cssclasses-testprop-testallprops-domprefixes
 */
;
window.Modernizr = function (a, b, c) {
    function y(a) {
        j.cssText = a
    }

    function z(a, b) {
        return y(prefixes.join(a + ";") + (b || ""))
    }

    function A(a, b) {
        return typeof a === b
    }

    function B(a, b) {
        return!!~("" + a).indexOf(b)
    }

    function C(a, b) {
        for (var d in a) {
            var e = a[d];
            if (!B(e, "-") && j[e] !== c)return b == "pfx" ? e : !0
        }
        return!1
    }

    function D(a, b, d) {
        for (var e in a) {
            var f = b[a[e]];
            if (f !== c)return d === !1 ? a[e] : A(f, "function") ? f.bind(d || b) : f
        }
        return!1
    }

    function E(a, b, c) {
        var d = a.charAt(0).toUpperCase() + a.slice(1), e = (a + " " + n.join(d + " ") + d).split(" ");
        return A(b, "string") || A(b, "undefined") ? C(e, b) : (e = (a + " " + o.join(d + " ") + d).split(" "), D(e, b, c))
    }

    var d = "2.6.2", e = {}, f = !0, g = b.documentElement, h = "modernizr", i = b.createElement(h), j = i.style, k, l = {}.toString, m = "Webkit Moz O ms", n = m.split(" "), o = m.toLowerCase().split(" "), p = {svg: "http://www.w3.org/2000/svg"}, q = {}, r = {}, s = {}, t = [], u = t.slice, v, w = {}.hasOwnProperty, x;
    !A(w, "undefined") && !A(w.call, "undefined") ? x = function (a, b) {
        return w.call(a, b)
    } : x = function (a, b) {
        return b in a && A(a.constructor.prototype[b], "undefined")
    }, Function.prototype.bind || (Function.prototype.bind = function (b) {
        var c = this;
        if (typeof c != "function")throw new TypeError;
        var d = u.call(arguments, 1), e = function () {
            if (this instanceof e) {
                var a = function () {
                };
                a.prototype = c.prototype;
                var f = new a, g = c.apply(f, d.concat(u.call(arguments)));
                return Object(g) === g ? g : f
            }
            return c.apply(b, d.concat(u.call(arguments)))
        };
        return e
    }), q.multiplebgs = function () {
        return y("background:url(https://),url(https://),red url(https://)"), /(url\s*\(.*?){3}/.test(j.background)
    }, q.backgroundsize = function () {
        return E("backgroundSize")
    }, q.csstransitions = function () {
        return E("transition")
    }, q.svg = function () {
        return!!b.createElementNS && !!b.createElementNS(p.svg, "svg").createSVGRect
    };
    for (var F in q)x(q, F) && (v = F.toLowerCase(), e[v] = q[F](), t.push((e[v] ? "" : "no-") + v));
    return e.addTest = function (a, b) {
        if (typeof a == "object")for (var d in a)x(a, d) && e.addTest(d, a[d]); else {
            a = a.toLowerCase();
            if (e[a] !== c)return e;
            b = typeof b == "function" ? b() : b, typeof f != "undefined" && f && (g.className += " " + (b ? "" : "no-") + a), e[a] = b
        }
        return e
    }, y(""), i = k = null, e._version = d, e._domPrefixes = o, e._cssomPrefixes = n, e.testProp = function (a) {
        return C([a])
    }, e.testAllProps = E, g.className = g.className.replace(/(^|\s)no-js(\s|$)/, "$1$2") + (f ? " js " + t.join(" ") : ""), e
}(this, this.document), function (a, b) {
    function k(a, b) {
        var c = a.createElement("p"), d = a.getElementsByTagName("head")[0] || a.documentElement;
        return c.innerHTML = "x<style>" + b + "</style>", d.insertBefore(c.lastChild, d.firstChild)
    }

    function l() {
        var a = r.elements;
        return typeof a == "string" ? a.split(" ") : a
    }

    function m(a) {
        var b = i[a[g]];
        return b || (b = {}, h++, a[g] = h, i[h] = b), b
    }

    function n(a, c, f) {
        c || (c = b);
        if (j)return c.createElement(a);
        f || (f = m(c));
        var g;
        return f.cache[a] ? g = f.cache[a].cloneNode() : e.test(a) ? g = (f.cache[a] = f.createElem(a)).cloneNode() : g = f.createElem(a), g.canHaveChildren && !d.test(a) ? f.frag.appendChild(g) : g
    }

    function o(a, c) {
        a || (a = b);
        if (j)return a.createDocumentFragment();
        c = c || m(a);
        var d = c.frag.cloneNode(), e = 0, f = l(), g = f.length;
        for (; e < g; e++)d.createElement(f[e]);
        return d
    }

    function p(a, b) {
        b.cache || (b.cache = {}, b.createElem = a.createElement, b.createFrag = a.createDocumentFragment, b.frag = b.createFrag()), a.createElement = function (c) {
            return r.shivMethods ? n(c, a, b) : b.createElem(c)
        }, a.createDocumentFragment = Function("h,f", "return function(){var n=f.cloneNode(),c=n.createElement;h.shivMethods&&(" + l().join().replace(/\w+/g, function (a) {
            return b.createElem(a), b.frag.createElement(a), 'c("' + a + '")'
        }) + ");return n}")(r, b.frag)
    }

    function q(a) {
        a || (a = b);
        var c = m(a);
        return r.shivCSS && !f && !c.hasCSS && (c.hasCSS = !!k(a, "article,aside,figcaption,figure,footer,header,hgroup,nav,section{display:block}mark{background:#FF0;color:#000}")), j || p(a, c), a
    }

    function v(a) {
        var b, c = a.getElementsByTagName("*"), d = c.length, e = RegExp("^(?:" + l().join("|") + ")$", "i"), f = [];
        while (d--)b = c[d], e.test(b.nodeName) && f.push(b.applyElement(w(b)));
        return f
    }

    function w(a) {
        var b, c = a.attributes, d = c.length, e = a.ownerDocument.createElement(t + ":" + a.nodeName);
        while (d--)b = c[d], b.specified && e.setAttribute(b.nodeName, b.nodeValue);
        return e.style.cssText = a.style.cssText, e
    }

    function x(a) {
        var b, c = a.split("{"), d = c.length, e = RegExp("(^|[\\s,>+~])(" + l().join("|") + ")(?=[[\\s,>+~#.:]|$)", "gi"), f = "$1" + t + "\\:$2";
        while (d--)b = c[d] = c[d].split("}"), b[b.length - 1] = b[b.length - 1].replace(e, f), c[d] = b.join("}");
        return c.join("{")
    }

    function y(a) {
        var b = a.length;
        while (b--)a[b].removeNode()
    }

    function z(a) {
        function g() {
            clearTimeout(d._removeSheetTimer), b && b.removeNode(!0), b = null
        }

        var b, c, d = m(a), e = a.namespaces, f = a.parentWindow;
        return!u || a.printShived ? a : (typeof e[t] == "undefined" && e.add(t), f.attachEvent("onbeforeprint", function () {
            g();
            var d, e, f, h = a.styleSheets, i = [], j = h.length, l = Array(j);
            while (j--)l[j] = h[j];
            while (f = l.pop())if (!f.disabled && s.test(f.media)) {
                try {
                    d = f.imports, e = d.length
                } catch (m) {
                    e = 0
                }
                for (j = 0; j < e; j++)l.push(d[j]);
                try {
                    i.push(f.cssText)
                } catch (m) {
                }
            }
            i = x(i.reverse().join("")), c = v(a), b = k(a, i)
        }), f.attachEvent("onafterprint", function () {
            y(c), clearTimeout(d._removeSheetTimer), d._removeSheetTimer = setTimeout(g, 500)
        }), a.printShived = !0, a)
    }

    var c = a.html5 || {}, d = /^<|^(?:button|map|select|textarea|object|iframe|option|optgroup)$/i, e = /^<|^(?:a|b|button|code|div|fieldset|form|h1|h2|h3|h4|h5|h6|i|iframe|img|input|label|li|link|ol|option|p|param|q|script|select|span|strong|style|table|tbody|td|textarea|tfoot|th|thead|tr|ul)$/i, f, g = "_html5shiv", h = 0, i = {}, j;
    (function () {
        try {
            var a = b.createElement("a");
            a.innerHTML = "<xyz></xyz>", f = "hidden"in a, j = a.childNodes.length == 1 || function () {
                b.createElement("a");
                var a = b.createDocumentFragment();
                return typeof a.cloneNode == "undefined" || typeof a.createDocumentFragment == "undefined" || typeof a.createElement == "undefined"
            }()
        } catch (c) {
            f = !0, j = !0
        }
    })();
    var r = {elements: c.elements || "abbr article aside audio bdi canvas data datalist details figcaption figure footer header hgroup mark meter nav output progress section summary time video", shivCSS: c.shivCSS !== !1, supportsUnknownElements: j, shivMethods: c.shivMethods !== !1, type: "default", shivDocument: q, createElement: n, createDocumentFragment: o};
    a.html5 = r, q(b);
    var s = /^$|\b(?:all|print)\b/, t = "html5shiv", u = !j && function () {
        var c = b.documentElement;
        return typeof b.namespaces != "undefined" && typeof b.parentWindow != "undefined" && typeof c.applyElement != "undefined" && typeof c.removeNode != "undefined" && typeof a.attachEvent != "undefined"
    }();
    r.type += " print", r.shivPrint = z, z(b)
}(this, document);