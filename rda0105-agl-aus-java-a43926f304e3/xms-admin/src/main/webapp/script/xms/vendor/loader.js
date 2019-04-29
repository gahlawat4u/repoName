(function () {
    var aa = "\n//# sourceURL=", ba = '<script type="text/javascript" src="', b = "American Samoa", ca = "Antigua and Barbuda", k = "Bolivia", p = "Bosna i Hercegovina", q = "Botswana", da = "British Virgin Islands", ea = "Cayman Islands", fa = "Christmas Island", ga = "Falkland Islands", t = "Ghana", ha = "Guin\u00e9e \u00e9quatoriale", ia = "Guyane fran\u00e7aise", u = "Honduras", v = "Indonesia", w = "Itoophiyaa", ja = "Kalaallit Nunaat", x = "Kiribati", ka = "LocaleNameConstants", la = "Luxembourg", ma = "Madagascar", na = "Marshall Islands", y = "Micronesia", oa = "Moldova, Republica",
        pa = "Nederlandse Antillen", qa = "New Zealand", z = "Nigeria", ra = "Norfolk Island", sa = "Northern Mariana Islands", ta = "Nouvelle-Cal\u00e9donie", A = "Papua New Guinea", ua = "Paraguay", va = "Philippines", wa = "Polyn\u00e9sie fran\u00e7aise", xa = "Puerto Rico", ya = "Rep\u00fablica Dominicana", B = "Rwanda", za = "Rywvaneth Unys", Aa = "R\u00e9publique centrafricaine", Ba = "R\u00e9publique d\u00e9mocratique du Congo", Ca = "Saint Kitts and Nevis", Da = "Saint Vincent and the Grenadines", Ea = "Saint-Pierre-et-Miquelon", Fa = "Serbia and Montenegro",
        Ga = "Seychelles", Ha = "Slovensk\u00e1 republika", Ia = "Solomon Islands", C = "South Africa", Ja = "Svalbard og Jan Mayen", Ka = "Swaziland", La = "S\u00e3o Tom\u00e9 e Pr\u00edncipe", D = "S\u00e9n\u00e9gal", Ma = "Tanzania", Na = "Timor Leste", F = "Tokelau", Oa = "Turks and Caicos Islands", G = "Tuvalu", H = "T\u00fcrkiye", Pa = "U.S. Virgin Islands", Qa = "United Kingdom", Ra = "United States", Sa = "United States Minor Outlying Islands", Ta = "Unknown or Invalid Region", I = "Vanuatu", Ua = "Wallis-et-Futuna", Va = "annotatedtimeline", J = "array", K = "browserchart",
        L = "complete", M = "corechart", N = "dygraph", O = "function", Wa = "google.charts.load", P = "imagechart", Q = "object", Xa = "text/javascript", R = "ui", S = "ui_base", T = "webfontloader", Ya = "{css_prefix}/{cssFile}", Za = "{prefix}", $a = "{prefix}/{version}/third_party/{package}", ab = "{version}", bb = "\u010cesk\u00e1 republika", cb = "\u0411\u0435\u043b\u0430\u0440\u0443\u0441\u044c", db = "\u041a\u044b\u0440\u0433\u044b\u0437\u0441\u0442\u0430\u043d", eb = "\u043c\u043e\u043d\u0433\u043e\u043b\u044c\u0441\u043a\u0438\u0439", fb = "\u0540\u0561\u0575\u0561\u057d\u057f\u0561\u0576\u056b \u0540\u0561\u0576\u0580\u0561\u057a\u0565\u057f\u0578\u0582\u0569\u056b\u0582\u0576",
        U = "\u0627\u0641\u063a\u0627\u0646\u0633\u062a\u0627\u0646", gb = "\u0627\u0644\u0627\u0645\u0627\u0631\u0627\u062a \u0627\u0644\u0639\u0631\u0628\u064a\u0629 \u0627\u0644\u0645\u062a\u062d\u062f\u0629", hb = "\u0627\u0644\u0635\u062d\u0631\u0627\u0621 \u0627\u0644\u063a\u0631\u0628\u064a\u0629", ib = "\u0627\u0644\u0645\u0645\u0644\u0643\u0629 \u0627\u0644\u0639\u0631\u0628\u064a\u0629 \u0627\u0644\u0633\u0639\u0648\u062f\u064a\u0629", jb = "\u0627\u0644\u0648\u0644\u0627\u064a\u0627\u062a \u0627\u0644\u0645\u062a\u062d\u062f\u0629 \u0627\u0644\u0623\u0645\u0631\u064a\u0643\u064a\u0629",
        kb = "\u062c\u0632\u0631 \u0627\u0644\u0642\u0645\u0631", V = "\u067e\u0627\u06a9\u0633\u062a\u0627\u0646", W = "\u092d\u093e\u0930\u0924", X = "\u12a2\u1275\u12ee\u1335\u12eb", lb = "\uc870\uc120 \ubbfc\uc8fc\uc8fc\uc758 \uc778\ubbfc \uacf5\ud654\uad6d";

    function Y() {
        return function () {
        }
    }

    var Z = Z || {};
    Z.global = this;
    Z.O = function (a) {
        return void 0 !== a
    };
    Z.xa = function (a, c, d) {
        a = a.split(".");
        d = d || Z.global;
        a[0]in d || !d.execScript || d.execScript("var " + a[0]);
        for (var e; a.length && (e = a.shift());)!a.length && Z.O(c) ? d[e] = c : d = d[e] ? d[e] : d[e] = {}
    };
    Z.De = function (a, c) {
        Z.xa(a, c)
    };
    Z.G = !0;
    Z.Jd = "en";
    Z.oa = !0;
    Z.fc = !1;
    Z.Pb = !Z.G;
    Z.Ya = !1;
    Z.Lf = function (a) {
        if (Z.Ka())throw Error("goog.provide can not be used within a goog.module.");
        Z.gb(a)
    };
    Z.gb = function (a, c) {
        Z.xa(a, c)
    };
    Z.pc = /^[a-zA-Z_$][a-zA-Z0-9._$]*$/;
    Z.Oa = function (a) {
        if (!Z.h(a) || !a || -1 == a.search(Z.pc))throw Error("Invalid module identifier");
        if (!Z.Ka())throw Error("Module " + a + " has been loaded incorrectly.");
        if (Z.l.Pa)throw Error("goog.module may only be called once per module.");
        Z.l.Pa = a
    };
    Z.Oa.get = Y();
    Z.Oa.Ne = Y();
    Z.l = null;
    Z.Ka = function () {
        return null != Z.l
    };
    Z.Oa.ua = function () {
        Z.l.ua = !0
    };
    Z.eg = function (a) {
        if (Z.Pb)throw a = a || "", Error("Importing test-only code into non-debug environment" + (a ? ": " + a : "."));
    };
    Z.Je = Y();
    Z.Ea = function (a) {
        a = a.split(".");
        for (var c = Z.global, d; d = a.shift();)if (Z.Mc(c[d]))c = c[d]; else return null;
        return c
    };
    Z.Ye = function (a, c) {
        c = c || Z.global;
        for (var d in a)c[d] = a[d]
    };
    Z.Pd = function (a, c, d, e) {
        if (Z.Wa) {
            var f;
            a = a.replace(/\\/g, "/");
            var g = Z.j;
            e && "boolean" !== typeof e || (e = e ? {module: "goog"} : {});
            for (var h = 0; f = c[h]; h++)g.S[f] = a, g.La[a] = e;
            for (e = 0; c = d[e]; e++)a in g.F || (g.F[a] = {}), g.F[a][c] = !0
        }
    };
    Z.Eg = !1;
    Z.Gd = !0;
    Z.Zc = function (a) {
        Z.global.console && Z.global.console.error(a)
    };
    Z.Zf = Y();
    Z.B = "";
    Z.Gf = Y();
    Z.Od = function () {
        throw Error("unimplemented abstract method");
    };
    Z.Qd = function (a) {
        a.Me = function () {
            if (a.ub)return a.ub;
            Z.G && (Z.vb[Z.vb.length] = a);
            return a.ub = new a
        }
    };
    Z.vb = [];
    Z.Wb = !0;
    Z.dc = Z.G;
    Z.Yc = {};
    Z.Wa = !1;
    Z.$a = "detect";
    Z.lc = "transpile.js";
    Z.Wa && (Z.j = {La: {}, S: {}, F: {}, Ib: {}, Va: {}, W: {}}, Z.sb = function () {
        var a = Z.global.document;
        return null != a && "write"in a
    }, Z.Ec = function () {
        if (Z.O(Z.global.Mb))Z.B = Z.global.Mb; else if (Z.sb())for (var a = Z.global.document.getElementsByTagName("SCRIPT"), c = a.length - 1; 0 <= c; --c) {
            var d = a[c].src, e = d.lastIndexOf("?"), e = -1 == e ? d.length : e;
            if ("base.js" == d.substr(e - 7, 7)) {
                Z.B = d.substr(0, e - 7);
                break
            }
        }
    }, Z.Ga = function (a, c) {
        (Z.global.zd || Z.wd)(a, c) && (Z.j.Va[a] = !0)
    }, Z.Vb = !(Z.global.atob || !Z.global.document || !Z.global.document.all),
        Z.Kc = function (a, c, d) {
            Z.Ga("", 'goog.retrieveAndExec_("' + a + '", ' + c + ", " + d + ");")
        }, Z.Ra = [], Z.Gg = function (a, c) {
        return Z.Wb && Z.O(Z.global.JSON) ? "goog.loadModule(" + Z.global.JSON.stringify(c + aa + a + "\n") + ");" : 'goog.loadModule(function(exports) {"use strict";' + c + "\n;return exports});\n//# sourceURL=" + a + "\n"
    }, Z.Xc = function () {
        var a = Z.Ra.length;
        if (0 < a) {
            var c = Z.Ra;
            Z.Ra = [];
            for (var d = 0; d < a; d++)Z.Ab(c[d])
        }
    }, Z.Af = function (a) {
        Z.wb(a) && Z.qc(a) && Z.Ab(Z.B + Z.Fa(a))
    }, Z.wb = function (a) {
        var c = (a = Z.Fa(a)) && Z.j.La[a] || {};
        return a &&
        ("goog" == c.module || Z.Bb(c.lang)) ? Z.B + a in Z.j.W : !1
    }, Z.qc = function (a) {
        if ((a = Z.Fa(a)) && a in Z.j.F)for (var c in Z.j.F[a])if (!Z.Qc(c) && !Z.wb(c))return !1;
        return !0
    }, Z.Ab = function (a) {
        if (a in Z.j.W) {
            var c = Z.j.W[a];
            delete Z.j.W[a];
            Z.Jc(c)
        }
    }, Z.zf = Y(), Z.vd = function (a) {
        Z.global.document.write(ba + a + '">\x3c/script>')
    }, Z.rc = function (a) {
        var c = Z.global.document, d = c.createElement("script");
        d.type = Xa;
        d.src = a;
        d.defer = !1;
        d.async = !1;
        c.head.appendChild(d)
    }, Z.wd = function (a, c) {
        if (Z.sb()) {
            var d = Z.global.document;
            if (!Z.Ya &&
                d.readyState == L) {
                if (/\bdeps.js$/.test(a))return !1;
                throw Error('Cannot write "' + a + '" after document load');
            }
            void 0 === c ? Z.Vb ? (c = " onreadystatechange='goog.onScriptLoad_(this, " + ++Z.yb + ")' ", d.write(ba + a + '"' + c + ">\x3c/script>")) : Z.Ya ? Z.rc(a) : Z.vd(a) : d.write('<script type="text/javascript">' + c + "\x3c/script>");
            return !0
        }
        return !1
    }, Z.Bb = function (a) {
        if ("always" == Z.$a)return !0;
        if ("never" == Z.$a)return !1;
        if (!Z.M) {
            Z.M = {es5: !0, es6: !0, "es6-impl": !0};
            try {
                Z.M.es5 = eval("[1,].length!=1"), eval('(()=>{"use strict";let a={};const X=class{constructor(){}x(z){return new Map([...arguments]).get(z[0])==3}};return new X().x([a,3])})()') &&
                (Z.M["es6-impl"] = !1), eval('(()=>{"use strict";class X{constructor(){if(new.target!=String)throw 1;this.x=42}}let q=Reflect.construct(X,[],String);if(q.x!=42||!(q instanceof String))throw 1;for(const a of[2,3]){if(a==2)continue;function f(z={a}){let a=0;return z.a}{function f(){return 0;}}return f()==3}})()') && (Z.M.es6 = !1)
            } catch (c) {
            }
        }
        return !!Z.M[a]
    }, Z.M = null, Z.yb = 0, Z.If = function (a, c) {
        a.readyState == L && Z.yb == c && Z.Xc();
        return !0
    }, Z.Hg = function (a) {
        function c(a) {
            if (!(a in f.Va || a in f.Ib)) {
                f.Ib[a] = !0;
                if (a in
                    f.F)for (var g in f.F[a])if (!Z.Qc(g))if (g in f.S)c(f.S[g]); else throw Error("Undefined nameToPath for " + g);
                a in e || (e[a] = !0, d.push(a))
            }
        }

        var d = [], e = {}, f = Z.j;
        c(a);
        for (a = 0; a < d.length; a++) {
            var g = d[a];
            Z.j.Va[g] = !0
        }
        var h = Z.l;
        Z.l = null;
        for (a = 0; a < d.length; a++)if (g = d[a]) {
            var l = f.La[g] || {}, m = Z.Bb(l.lang);
            "goog" == l.module || m ? Z.Kc(Z.B + g, "goog" == l.module, m) : Z.Ga(Z.B + g)
        } else throw Z.l = h, Error("Undefined script input");
        Z.l = h
    }, Z.Fa = function (a) {
        return a in Z.j.S ? Z.j.S[a] : null
    }, Z.Ec(), Z.global.Ad || Z.Ga(Z.B + "deps.js"));
    Z.yf = function (a) {
        var c = Z.l;
        try {
            Z.l = {Pa: void 0, ua: !1};
            var d;
            if (Z.xb(a))d = a.call(void 0, {}); else if (Z.h(a))d = Z.Vc.call(void 0, a); else throw Error("Invalid module definition");
            var e = Z.l.Pa;
            if (!Z.h(e) || !e)throw Error('Invalid module name "' + e + '"');
            Z.l.ua ? Z.gb(e, d) : Z.dc && Object.seal && Object.seal(d);
            Z.Yc[e] = d
        } finally {
            Z.l = c
        }
    };
    Z.Vc = function (a) {
        eval(a);
        return {}
    };
    Z.Df = function (a) {
        a = a.split("/");
        for (var c = 0; c < a.length;)"." == a[c] ? a.splice(c, 1) : c && ".." == a[c] && a[c - 1] && ".." != a[c - 1] ? a.splice(--c, 2) : c++;
        return a.join("/")
    };
    Z.Uc = function (a) {
        if (Z.global.Nb)return Z.global.Nb(a);
        try {
            var c = new Z.global.XMLHttpRequest;
            c.open("get", a, !1);
            c.send();
            return 0 == c.status || 200 == c.status ? c.responseText : null
        } catch (d) {
            return null
        }
    };
    Z.$f = Y();
    Z.zg = function (a, c) {
        var d = Z.global.$jscomp;
        d || (Z.global.$jscomp = d = {});
        var e = d.Fb;
        if (!e) {
            var f = Z.B + Z.lc, g = Z.Uc(f);
            g && (eval(g + aa + f), d = Z.global.$jscomp, e = d.Fb)
        }
        if (!e)var h = " requires transpilation but no transpiler was found.", h = h + ' Please add "//javascript/closure:transpiler" as a data dependency to ensure it is included.', e = d.Fb = function (a, c) {
            Z.Zc(c + h);
            return a
        };
        return e(a, c)
    };
    Z.u = function (a) {
        var c = typeof a;
        if (c == Q)if (a) {
            if (a instanceof Array)return J;
            if (a instanceof Object)return c;
            var d = Object.prototype.toString.call(a);
            if ("[object Window]" == d)return Q;
            if ("[object Array]" == d || "number" == typeof a.length && "undefined" != typeof a.splice && "undefined" != typeof a.propertyIsEnumerable && !a.propertyIsEnumerable("splice"))return J;
            if ("[object Function]" == d || "undefined" != typeof a.call && "undefined" != typeof a.propertyIsEnumerable && !a.propertyIsEnumerable("call"))return O
        } else return "null";
        else if (c == O && "undefined" == typeof a.call)return Q;
        return c
    };
    Z.pf = function (a) {
        return null === a
    };
    Z.Mc = function (a) {
        return null != a
    };
    Z.isArray = function (a) {
        return Z.u(a) == J
    };
    Z.Ia = function (a) {
        var c = Z.u(a);
        return c == J || c == Q && "number" == typeof a.length
    };
    Z.kf = function (a) {
        return Z.Z(a) && typeof a.getFullYear == O
    };
    Z.h = function (a) {
        return "string" == typeof a
    };
    Z.Lc = function (a) {
        return "boolean" == typeof a
    };
    Z.Pc = function (a) {
        return "number" == typeof a
    };
    Z.xb = function (a) {
        return Z.u(a) == O
    };
    Z.Z = function (a) {
        var c = typeof a;
        return c == Q && null != a || c == O
    };
    Z.qb = function (a) {
        return a[Z.D] || (a[Z.D] = ++Z.qd)
    };
    Z.Ze = function (a) {
        return !!a[Z.D]
    };
    Z.fd = function (a) {
        null !== a && "removeAttribute"in a && a.removeAttribute(Z.D);
        try {
            delete a[Z.D]
        } catch (c) {
        }
    };
    Z.D = "closure_uid_" + (1E9 * Math.random() >>> 0);
    Z.qd = 0;
    Z.Le = Z.qb;
    Z.Wf = Z.fd;
    Z.xc = function (a) {
        var c = Z.u(a);
        if (c == Q || c == J) {
            if (a.clone)return a.clone();
            var c = c == J ? [] : {}, d;
            for (d in a)c[d] = Z.xc(a[d]);
            return c
        }
        return a
    };
    Z.vc = function (a, c, d) {
        return a.call.apply(a.bind, arguments)
    };
    Z.uc = function (a, c, d) {
        if (!a)throw Error();
        if (2 < arguments.length) {
            var e = Array.prototype.slice.call(arguments, 2);
            return function () {
                var d = Array.prototype.slice.call(arguments);
                Array.prototype.unshift.apply(d, e);
                return a.apply(c, d)
            }
        }
        return function () {
            return a.apply(c, arguments)
        }
    };
    Z.bind = function (a, c, d) {
        Function.prototype.bind && -1 != Function.prototype.bind.toString().indexOf("native code") ? Z.bind = Z.vc : Z.bind = Z.uc;
        return Z.bind.apply(null, arguments)
    };
    Z.cd = function (a, c) {
        var d = Array.prototype.slice.call(arguments, 1);
        return function () {
            var c = d.slice();
            c.push.apply(c, arguments);
            return a.apply(this, c)
        }
    };
    Z.Bf = function (a, c) {
        for (var d in c)a[d] = c[d]
    };
    Z.now = Z.oa && Date.now || function () {
            return +new Date
        };
    Z.Jc = function (a) {
        if (Z.global.execScript)Z.global.execScript(a, "JavaScript"); else if (Z.global.eval) {
            if (null == Z.X)if (Z.global.eval("var _evalTest_ = 1;"), "undefined" != typeof Z.global._evalTest_) {
                try {
                    delete Z.global._evalTest_
                } catch (e) {
                }
                Z.X = !0
            } else Z.X = !1;
            if (Z.X)Z.global.eval(a); else {
                var c = Z.global.document, d = c.createElement("SCRIPT");
                d.type = Xa;
                d.defer = !1;
                d.appendChild(c.createTextNode(a));
                c.body.appendChild(d);
                c.body.removeChild(d)
            }
        } else throw Error("goog.globalEval not available");
    };
    Z.X = null;
    Z.Ke = function (a, c) {
        function d(a) {
            a = a.split("-");
            for (var c = [], d = 0; d < a.length; d++)c.push(e(a[d]));
            return c.join("-")
        }

        function e(a) {
            return Z.hb[a] || a
        }

        var f;
        f = Z.hb ? "BY_WHOLE" == Z.Ac ? e : d : function (a) {
            return a
        };
        return c ? a + "-" + f(c) : f(a)
    };
    Z.ag = function (a, c) {
        Z.hb = a;
        Z.Ac = c
    };
    Z.Qe = function (a, c) {
        c && (a = a.replace(/\{\$([^}]+)}/g, function (a, e) {
            return null != c && e in c ? c[e] : a
        }));
        return a
    };
    Z.Re = function (a) {
        return a
    };
    Z.ya = function (a, c) {
        Z.xa(a, c, void 0)
    };
    Z.He = function (a, c, d) {
        a[c] = d
    };
    Z.Ha = function (a, c) {
        function d() {
        }

        d.prototype = c.prototype;
        a.ma = c.prototype;
        a.prototype = new d;
        a.prototype.constructor = a;
        a.tc = function (a, d, g) {
            for (var h = Array(arguments.length - 2), l = 2; l < arguments.length; l++)h[l - 2] = arguments[l];
            return c.prototype[d].apply(a, h)
        }
    };
    Z.tc = function (a, c, d) {
        var e = arguments.callee.caller;
        if (Z.fc || Z.G && !e)throw Error("arguments.caller not defined.  goog.base() cannot be used with strict mode code. See http://www.ecma-international.org/ecma-262/5.1/#sec-C");
        if (e.ma) {
            for (var f = Array(arguments.length - 1), g = 1; g < arguments.length; g++)f[g - 1] = arguments[g];
            return e.ma.constructor.apply(a, f)
        }
        f = Array(arguments.length - 2);
        for (g = 2; g < arguments.length; g++)f[g - 2] = arguments[g];
        for (var g = !1, h = a.constructor; h; h = h.ma && h.ma.constructor)if (h.prototype[c] ===
            e)g = !0; else if (g)return h.prototype[c].apply(a, f);
        if (a[c] === e)return a.constructor.prototype[c].apply(a, f);
        throw Error("goog.base called from a method of one name to a method of a different name");
    };
    Z.scope = function (a) {
        if (Z.Ka())throw Error("goog.scope is not supported within a goog.module.");
        a.call(Z.global)
    };
    Z.o = function (a, c) {
        var d = c.constructor, e = c.ld;
        d && d != Object.prototype.constructor || (d = function () {
            throw Error("cannot instantiate an interface (no constructor defined).");
        });
        d = Z.o.yc(d, a);
        a && Z.Ha(d, a);
        delete c.constructor;
        delete c.ld;
        Z.o.bb(d.prototype, c);
        null != e && (e instanceof Function ? e(d) : Z.o.bb(d, e));
        return d
    };
    Z.o.cc = Z.G;
    Z.o.yc = function (a, c) {
        function d() {
            var c = a.apply(this, arguments) || this;
            c[Z.D] = c[Z.D];
            this.constructor === d && e && Object.seal instanceof Function && Object.seal(c);
            return c
        }

        if (!Z.o.cc)return a;
        var e = !Z.o.Rc(c);
        return d
    };
    Z.o.Rc = function (a) {
        return a && a.prototype && a.prototype[Z.mc]
    };
    Z.o.Za = "constructor hasOwnProperty isPrototypeOf propertyIsEnumerable toLocaleString toString valueOf".split(" ");
    Z.o.bb = function (a, c) {
        for (var d in c)Object.prototype.hasOwnProperty.call(c, d) && (a[d] = c[d]);
        for (var e = 0; e < Z.o.Za.length; e++)d = Z.o.Za[e], Object.prototype.hasOwnProperty.call(c, d) && (a[d] = c[d])
    };
    Z.qg = Y();
    Z.mc = "goog_defineClass_legacy_unsealable";
    Z.debug = {};
    Z.debug.Error = function (a) {
        if (Error.captureStackTrace)Error.captureStackTrace(this, Z.debug.Error); else {
            var c = Error().stack;
            c && (this.stack = c)
        }
        a && (this.message = String(a))
    };
    Z.Ha(Z.debug.Error, Error);
    Z.debug.Error.prototype.name = "CustomError";
    Z.ib = {};
    Z.ib.ac = {Qb: 1, xd: 2, Nd: 3, yd: 4, Id: 5, Hd: 6, Md: 7, Bd: 8, Dd: 9, Fd: 10, Ed: 11, Kd: 12};
    Z.b = {};
    Z.b.Xa = !1;
    Z.b.Sb = !1;
    Z.b.nc = {Zb: "\u00a0"};
    Z.b.startsWith = function (a, c) {
        return 0 == a.lastIndexOf(c, 0)
    };
    Z.b.endsWith = function (a, c) {
        var d = a.length - c.length;
        return 0 <= d && a.indexOf(c, d) == d
    };
    Z.b.re = function (a, c) {
        return 0 == Z.b.fb(c, a.substr(0, c.length))
    };
    Z.b.pe = function (a, c) {
        return 0 == Z.b.fb(c, a.substr(a.length - c.length, c.length))
    };
    Z.b.qe = function (a, c) {
        return a.toLowerCase() == c.toLowerCase()
    };
    Z.b.md = function (a, c) {
        for (var d = a.split("%s"), e = "", f = Array.prototype.slice.call(arguments, 1); f.length && 1 < d.length;)e += d.shift() + f.shift();
        return e + d.join("%s")
    };
    Z.b.we = function (a) {
        return a.replace(/[\s\xa0]+/g, " ").replace(/^\s+|\s+$/g, "")
    };
    Z.b.Ja = function (a) {
        return /^[\s\xa0]*$/.test(a)
    };
    Z.b.mf = function (a) {
        return 0 == a.length
    };
    Z.b.Nc = Z.b.Ja;
    Z.b.Oc = function (a) {
        return Z.b.Ja(Z.b.$c(a))
    };
    Z.b.lf = Z.b.Oc;
    Z.b.jf = function (a) {
        return !/[^\t\n\r ]/.test(a)
    };
    Z.b.gf = function (a) {
        return !/[^a-zA-Z]/.test(a)
    };
    Z.b.qf = function (a) {
        return !/[^0-9]/.test(a)
    };
    Z.b.hf = function (a) {
        return !/[^a-zA-Z0-9]/.test(a)
    };
    Z.b.tf = function (a) {
        return " " == a
    };
    Z.b.uf = function (a) {
        return 1 == a.length && " " <= a && "~" >= a || "\u0080" <= a && "\ufffd" >= a
    };
    Z.b.og = function (a) {
        return a.replace(/(\r\n|\r|\n)+/g, " ")
    };
    Z.b.le = function (a) {
        return a.replace(/(\r\n|\r|\n)/g, "\n")
    };
    Z.b.Ff = function (a) {
        return a.replace(/\xa0|\s/g, " ")
    };
    Z.b.Ef = function (a) {
        return a.replace(/\xa0|[ \t]+/g, " ")
    };
    Z.b.ue = function (a) {
        return a.replace(/[\t\r\n ]+/g, " ").replace(/^[\t\r\n ]+|[\t\r\n ]+$/g, "")
    };
    Z.b.trim = Z.oa && String.prototype.trim ? function (a) {
        return a.trim()
    } : function (a) {
        return a.replace(/^[\s\xa0]+|[\s\xa0]+$/g, "")
    };
    Z.b.trimLeft = function (a) {
        return a.replace(/^[\s\xa0]+/, "")
    };
    Z.b.trimRight = function (a) {
        return a.replace(/[\s\xa0]+$/, "")
    };
    Z.b.fb = function (a, c) {
        a = String(a).toLowerCase();
        c = String(c).toLowerCase();
        return a < c ? -1 : a == c ? 0 : 1
    };
    Z.b.Db = function (a, c, d) {
        if (a == c)return 0;
        if (!a)return -1;
        if (!c)return 1;
        for (var e = a.toLowerCase().match(d), f = c.toLowerCase().match(d), g = Math.min(e.length, f.length), h = 0; h < g; h++) {
            d = e[h];
            var l = f[h];
            if (d != l)return a = parseInt(d, 10), !isNaN(a) && (c = parseInt(l, 10), !isNaN(c) && a - c) ? a - c : d < l ? -1 : 1
        }
        return e.length != f.length ? e.length - f.length : a < c ? -1 : 1
    };
    Z.b.df = function (a, c) {
        return Z.b.Db(a, c, /\d+|\D+/g)
    };
    Z.b.Hc = function (a, c) {
        return Z.b.Db(a, c, /\d+|\.\d+|\D+/g)
    };
    Z.b.Hf = Z.b.Hc;
    Z.b.Dg = function (a) {
        return encodeURIComponent(String(a))
    };
    Z.b.Cg = function (a) {
        return decodeURIComponent(a.replace(/\+/g, " "))
    };
    Z.b.ad = function (a, c) {
        return a.replace(/(\r\n|\r|\n)/g, c ? "<br />" : "<br>")
    };
    Z.b.rb = function (a) {
        if (!Z.b.Kb.test(a))return a;
        -1 != a.indexOf("&") && (a = a.replace(Z.b.Lb, "&amp;"));
        -1 != a.indexOf("<") && (a = a.replace(Z.b.Yb, "&lt;"));
        -1 != a.indexOf(">") && (a = a.replace(Z.b.Tb, "&gt;"));
        -1 != a.indexOf('"') && (a = a.replace(Z.b.bc, "&quot;"));
        -1 != a.indexOf("'") && (a = a.replace(Z.b.ec, "&#39;"));
        -1 != a.indexOf("\x00") && (a = a.replace(Z.b.$b, "&#0;"));
        Z.b.Xa && -1 != a.indexOf("e") && (a = a.replace(Z.b.Rb, "&#101;"));
        return a
    };
    Z.b.Lb = /&/g;
    Z.b.Yb = /</g;
    Z.b.Tb = />/g;
    Z.b.bc = /"/g;
    Z.b.ec = /'/g;
    Z.b.$b = /\x00/g;
    Z.b.Rb = /e/g;
    Z.b.Kb = Z.b.Xa ? /[\x00&<>"'e]/ : /[\x00&<>"']/;
    Z.b.Gb = function (a) {
        return Z.b.contains(a, "&") ? !Z.b.Sb && "document"in Z.global ? Z.b.Hb(a) : Z.b.rd(a) : a
    };
    Z.b.Bg = function (a, c) {
        return Z.b.contains(a, "&") ? Z.b.Hb(a, c) : a
    };
    Z.b.Hb = function (a, c) {
        var d = {"&amp;": "&", "&lt;": "<", "&gt;": ">", "&quot;": '"'}, e;
        e = c ? c.createElement("div") : Z.global.document.createElement("div");
        return a.replace(Z.b.Ub, function (a, c) {
            var h = d[a];
            if (h)return h;
            "#" == c.charAt(0) && (c = Number("0" + c.substr(1)), isNaN(c) || (h = String.fromCharCode(c)));
            h || (e.innerHTML = a + " ", h = e.firstChild.nodeValue.slice(0, -1));
            return d[a] = h
        })
    };
    Z.b.rd = function (a) {
        return a.replace(/&([^;]+);/g, function (a, d) {
            switch (d) {
                case "amp":
                    return "&";
                case "lt":
                    return "<";
                case "gt":
                    return ">";
                case "quot":
                    return '"';
                default:
                    return "#" != d.charAt(0) || (d = Number("0" + d.substr(1)), isNaN(d)) ? a : String.fromCharCode(d)
            }
        })
    };
    Z.b.Ub = /&([^;\s<&]+);?/g;
    Z.b.Fg = function (a, c) {
        return Z.b.ad(a.replace(/  /g, " &#160;"), c)
    };
    Z.b.Kf = function (a) {
        return a.replace(/(^|[\n ]) /g, "$1" + Z.b.nc.Zb)
    };
    Z.b.pg = function (a, c) {
        for (var d = c.length, e = 0; e < d; e++) {
            var f = 1 == d ? c : c.charAt(e);
            if (a.charAt(0) == f && a.charAt(a.length - 1) == f)return a.substring(1, a.length - 1)
        }
        return a
    };
    Z.b.truncate = function (a, c, d) {
        d && (a = Z.b.Gb(a));
        a.length > c && (a = a.substring(0, c - 3) + "...");
        d && (a = Z.b.rb(a));
        return a
    };
    Z.b.Ag = function (a, c, d, e) {
        d && (a = Z.b.Gb(a));
        e && a.length > c ? (e > c && (e = c), a = a.substring(0, c - e) + "..." + a.substring(a.length - e)) : a.length > c && (e = Math.floor(c / 2), a = a.substring(0, e + c % 2) + "..." + a.substring(a.length - e));
        d && (a = Z.b.rb(a));
        return a
    };
    Z.b.Ua = {
        "\x00": "\\0",
        "\b": "\\b",
        "\f": "\\f",
        "\n": "\\n",
        "\r": "\\r",
        "\t": "\\t",
        "\x0B": "\\x0B",
        '"': '\\"',
        "\\": "\\\\",
        "<": "<"
    };
    Z.b.$ = {"'": "\\'"};
    Z.b.quote = function (a) {
        a = String(a);
        for (var c = ['"'], d = 0; d < a.length; d++) {
            var e = a.charAt(d), f = e.charCodeAt(0);
            c[d + 1] = Z.b.Ua[e] || (31 < f && 127 > f ? e : Z.b.jb(e))
        }
        c.push('"');
        return c.join("")
    };
    Z.b.Ge = function (a) {
        for (var c = [], d = 0; d < a.length; d++)c[d] = Z.b.jb(a.charAt(d));
        return c.join("")
    };
    Z.b.jb = function (a) {
        if (a in Z.b.$)return Z.b.$[a];
        if (a in Z.b.Ua)return Z.b.$[a] = Z.b.Ua[a];
        var c, d = a.charCodeAt(0);
        if (31 < d && 127 > d)c = a; else {
            if (256 > d) {
                if (c = "\\x", 16 > d || 256 < d)c += "0"
            } else c = "\\u", 4096 > d && (c += "0");
            c += d.toString(16).toUpperCase()
        }
        return Z.b.$[a] = c
    };
    Z.b.contains = function (a, c) {
        return -1 != a.indexOf(c)
    };
    Z.b.oe = function (a, c) {
        return Z.b.contains(a.toLowerCase(), c.toLowerCase())
    };
    Z.b.Be = function (a, c) {
        return a && c ? a.split(c).length - 1 : 0
    };
    Z.b.L = function (a, c, d) {
        var e = a;
        0 <= c && c < a.length && 0 < d && (e = a.substr(0, c) + a.substr(c + d, a.length - c - d));
        return e
    };
    Z.b.remove = function (a, c) {
        c = new RegExp(Z.b.Sa(c), "");
        return a.replace(c, "")
    };
    Z.b.Tf = function (a, c) {
        c = new RegExp(Z.b.Sa(c), "g");
        return a.replace(c, "")
    };
    Z.b.Sa = function (a) {
        return String(a).replace(/([-()\[\]{}+?*.$\^|,:#<!\\])/g, "\\$1").replace(/\x08/g, "\\x08")
    };
    Z.b.repeat = String.prototype.repeat ? function (a, c) {
        return a.repeat(c)
    } : function (a, c) {
        return Array(c + 1).join(a)
    };
    Z.b.Jf = function (a, c, d) {
        a = Z.O(d) ? a.toFixed(d) : String(a);
        d = a.indexOf(".");
        -1 == d && (d = a.length);
        return Z.b.repeat("0", Math.max(0, c - d)) + a
    };
    Z.b.$c = function (a) {
        return null == a ? "" : String(a)
    };
    Z.b.ke = function (a) {
        return Array.prototype.join.call(arguments, "")
    };
    Z.b.Ue = function () {
        return Math.floor(2147483648 * Math.random()).toString(36) + Math.abs(Math.floor(2147483648 * Math.random()) ^ Z.now()).toString(36)
    };
    Z.b.ye = function (a, c) {
        var d = 0;
        a = Z.b.trim(String(a)).split(".");
        c = Z.b.trim(String(c)).split(".");
        for (var e = Math.max(a.length, c.length), f = 0; 0 == d && f < e; f++) {
            var g = a[f] || "", h = c[f] || "", l = /(\d*)(\D*)/g, m = /(\d*)(\D*)/g;
            do {
                var n = l.exec(g) || ["", "", ""], r = m.exec(h) || ["", "", ""];
                if (0 == n[0].length && 0 == r[0].length)break;
                d = Z.b.qa(0 == n[1].length ? 0 : parseInt(n[1], 10), 0 == r[1].length ? 0 : parseInt(r[1], 10)) || Z.b.qa(0 == n[2].length, 0 == r[2].length) || Z.b.qa(n[2], r[2])
            } while (0 == d)
        }
        return d
    };
    Z.b.qa = function (a, c) {
        return a < c ? -1 : a > c ? 1 : 0
    };
    Z.b.$e = function (a) {
        for (var c = 0, d = 0; d < a.length; ++d)c = 31 * c + a.charCodeAt(d) >>> 0;
        return c
    };
    Z.b.td = 2147483648 * Math.random() | 0;
    Z.b.Ce = function () {
        return "goog_" + Z.b.td++
    };
    Z.b.vg = function (a) {
        var c = Number(a);
        return 0 == c && Z.b.Ja(a) ? NaN : c
    };
    Z.b.nf = function (a) {
        return /^[a-z]+([A-Z][a-z]*)*$/.test(a)
    };
    Z.b.vf = function (a) {
        return /^([A-Z][a-z]*)+$/.test(a)
    };
    Z.b.rg = function (a) {
        return String(a).replace(/\-([a-z])/g, function (a, d) {
            return d.toUpperCase()
        })
    };
    Z.b.xg = function (a) {
        return String(a).replace(/([A-Z])/g, "-$1").toLowerCase()
    };
    Z.b.yg = function (a, c) {
        c = Z.h(c) ? Z.b.Sa(c) : "\\s";
        return a.replace(new RegExp("(^" + (c ? "|[" + c + "]+" : "") + ")([a-z])", "g"), function (a, c, f) {
            return c + f.toUpperCase()
        })
    };
    Z.b.me = function (a) {
        return String(a.charAt(0)).toUpperCase() + String(a.substr(1)).toLowerCase()
    };
    Z.b.parseInt = function (a) {
        isFinite(a) && (a = String(a));
        return Z.h(a) ? /^\s*-?0x/i.test(a) ? parseInt(a, 16) : parseInt(a, 10) : NaN
    };
    Z.b.hg = function (a, c, d) {
        a = a.split(c);
        for (var e = []; 0 < d && a.length;)e.push(a.shift()), d--;
        a.length && e.push(a.join(c));
        return e
    };
    Z.b.xf = function (a, c) {
        if (c)"string" == typeof c && (c = [c]); else return a;
        for (var d = -1, e = 0; e < c.length; e++)if ("" != c[e]) {
            var f = a.lastIndexOf(c[e]);
            f > d && (d = f)
        }
        return -1 == d ? a : a.slice(d + 1)
    };
    Z.b.Ee = function (a, c) {
        var d = [], e = [];
        if (a == c)return 0;
        if (!a.length || !c.length)return Math.max(a.length, c.length);
        for (var f = 0; f < c.length + 1; f++)d[f] = f;
        for (f = 0; f < a.length; f++) {
            e[0] = f + 1;
            for (var g = 0; g < c.length; g++)e[g + 1] = Math.min(e[g] + 1, d[g + 1] + 1, d[g] + Number(a[f] != c[g]));
            for (g = 0; g < d.length; g++)d[g] = e[g]
        }
        return e[c.length]
    };
    Z.g = {};
    Z.g.s = Z.G;
    Z.g.U = function (a, c) {
        c.unshift(a);
        Z.debug.Error.call(this, Z.b.md.apply(null, c));
        c.shift()
    };
    Z.Ha(Z.g.U, Z.debug.Error);
    Z.g.U.prototype.name = "AssertionError";
    Z.g.Ob = function (a) {
        throw a;
    };
    Z.g.va = Z.g.Ob;
    Z.g.A = function (a, c, d, e) {
        var f = "Assertion failed";
        if (d)var f = f + (": " + d), g = e; else a && (f += ": " + a, g = c);
        a = new Z.g.U("" + f, g || []);
        Z.g.va(a)
    };
    Z.g.cg = function (a) {
        Z.g.s && (Z.g.va = a)
    };
    Z.g.assert = function (a, c, d) {
        Z.g.s && !a && Z.g.A("", null, c, Array.prototype.slice.call(arguments, 2));
        return a
    };
    Z.g.Aa = function (a, c) {
        Z.g.s && Z.g.va(new Z.g.U("Failure" + (a ? ": " + a : ""), Array.prototype.slice.call(arguments, 1)))
    };
    Z.g.Wd = function (a, c, d) {
        Z.g.s && !Z.Pc(a) && Z.g.A("Expected number but got %s: %s.", [Z.u(a), a], c, Array.prototype.slice.call(arguments, 2));
        return a
    };
    Z.g.Zd = function (a, c, d) {
        Z.g.s && !Z.h(a) && Z.g.A("Expected string but got %s: %s.", [Z.u(a), a], c, Array.prototype.slice.call(arguments, 2));
        return a
    };
    Z.g.Ud = function (a, c, d) {
        Z.g.s && !Z.xb(a) && Z.g.A("Expected function but got %s: %s.", [Z.u(a), a], c, Array.prototype.slice.call(arguments, 2));
        return a
    };
    Z.g.Xd = function (a, c, d) {
        Z.g.s && !Z.Z(a) && Z.g.A("Expected object but got %s: %s.", [Z.u(a), a], c, Array.prototype.slice.call(arguments, 2));
        return a
    };
    Z.g.Rd = function (a, c, d) {
        Z.g.s && !Z.isArray(a) && Z.g.A("Expected array but got %s: %s.", [Z.u(a), a], c, Array.prototype.slice.call(arguments, 2));
        return a
    };
    Z.g.Sd = function (a, c, d) {
        Z.g.s && !Z.Lc(a) && Z.g.A("Expected boolean but got %s: %s.", [Z.u(a), a], c, Array.prototype.slice.call(arguments, 2));
        return a
    };
    Z.g.Td = function (a, c, d) {
        !Z.g.s || Z.Z(a) && a.nodeType == Z.ib.ac.Qb || Z.g.A("Expected Element but got %s: %s.", [Z.u(a), a], c, Array.prototype.slice.call(arguments, 2));
        return a
    };
    Z.g.Vd = function (a, c, d, e) {
        !Z.g.s || a instanceof c || Z.g.A("Expected instanceof %s but got %s.", [Z.g.pb(c), Z.g.pb(a)], d, Array.prototype.slice.call(arguments, 3));
        return a
    };
    Z.g.Yd = function () {
        for (var a in Object.prototype)Z.g.Aa(a + " should not be enumerable in Object.prototype.")
    };
    Z.g.pb = function (a) {
        return a instanceof Function ? a.displayName || a.name || "unknown type name" : a instanceof Object ? a.constructor.displayName || a.constructor.name || Object.prototype.toString.call(a) : null === a ? "null" : typeof a
    };
    Z.f = {};
    Z.w = Z.oa;
    Z.f.v = !1;
    Z.f.dd = function (a) {
        return a[a.length - 1]
    };
    Z.f.wf = Z.f.dd;
    Z.f.indexOf = Z.w && (Z.f.v || Array.prototype.indexOf) ? function (a, c, d) {
        return Array.prototype.indexOf.call(a, c, d)
    } : function (a, c, d) {
        d = null == d ? 0 : 0 > d ? Math.max(0, a.length + d) : d;
        if (Z.h(a))return Z.h(c) && 1 == c.length ? a.indexOf(c, d) : -1;
        for (; d < a.length; d++)if (d in a && a[d] === c)return d;
        return -1
    };
    Z.f.lastIndexOf = Z.w && (Z.f.v || Array.prototype.lastIndexOf) ? function (a, c, d) {
        return Array.prototype.lastIndexOf.call(a, c, null == d ? a.length - 1 : d)
    } : function (a, c, d) {
        d = null == d ? a.length - 1 : d;
        0 > d && (d = Math.max(0, a.length + d));
        if (Z.h(a))return Z.h(c) && 1 == c.length ? a.lastIndexOf(c, d) : -1;
        for (; 0 <= d; d--)if (d in a && a[d] === c)return d;
        return -1
    };
    Z.f.forEach = Z.w && (Z.f.v || Array.prototype.forEach) ? function (a, c, d) {
        Array.prototype.forEach.call(a, c, d)
    } : function (a, c, d) {
        for (var e = a.length, f = Z.h(a) ? a.split("") : a, g = 0; g < e; g++)g in f && c.call(d, f[g], g, a)
    };
    Z.f.kb = function (a, c) {
        for (var d = Z.h(a) ? a.split("") : a, e = a.length - 1; 0 <= e; --e)e in d && c.call(void 0, d[e], e, a)
    };
    Z.f.filter = Z.w && (Z.f.v || Array.prototype.filter) ? function (a, c, d) {
        return Array.prototype.filter.call(a, c, d)
    } : function (a, c, d) {
        for (var e = a.length, f = [], g = 0, h = Z.h(a) ? a.split("") : a, l = 0; l < e; l++)if (l in h) {
            var m = h[l];
            c.call(d, m, l, a) && (f[g++] = m)
        }
        return f
    };
    Z.f.map = Z.w && (Z.f.v || Array.prototype.map) ? function (a, c, d) {
        return Array.prototype.map.call(a, c, d)
    } : function (a, c, d) {
        for (var e = a.length, f = Array(e), g = Z.h(a) ? a.split("") : a, h = 0; h < e; h++)h in g && (f[h] = c.call(d, g[h], h, a));
        return f
    };
    Z.f.reduce = Z.w && (Z.f.v || Array.prototype.reduce) ? function (a, c, d, e) {
        e && (c = Z.bind(c, e));
        return Array.prototype.reduce.call(a, c, d)
    } : function (a, c, d, e) {
        var f = d;
        Z.f.forEach(a, function (d, h) {
            f = c.call(e, f, d, h, a)
        });
        return f
    };
    Z.f.reduceRight = Z.w && (Z.f.v || Array.prototype.reduceRight) ? function (a, c, d, e) {
        e && (c = Z.bind(c, e));
        return Array.prototype.reduceRight.call(a, c, d)
    } : function (a, c, d, e) {
        var f = d;
        Z.f.kb(a, function (d, h) {
            f = c.call(e, f, d, h, a)
        });
        return f
    };
    Z.f.some = Z.w && (Z.f.v || Array.prototype.some) ? function (a, c, d) {
        return Array.prototype.some.call(a, c, d)
    } : function (a, c, d) {
        for (var e = a.length, f = Z.h(a) ? a.split("") : a, g = 0; g < e; g++)if (g in f && c.call(d, f[g], g, a))return !0;
        return !1
    };
    Z.f.every = Z.w && (Z.f.v || Array.prototype.every) ? function (a, c, d) {
        return Array.prototype.every.call(a, c, d)
    } : function (a, c, d) {
        for (var e = a.length, f = Z.h(a) ? a.split("") : a, g = 0; g < e; g++)if (g in f && !c.call(d, f[g], g, a))return !1;
        return !0
    };
    Z.f.count = function (a, c, d) {
        var e = 0;
        Z.f.forEach(a, function (a, g, h) {
            c.call(d, a, g, h) && ++e
        }, d);
        return e
    };
    Z.f.find = function (a, c, d) {
        c = Z.f.findIndex(a, c, d);
        return 0 > c ? null : Z.h(a) ? a.charAt(c) : a[c]
    };
    Z.f.findIndex = function (a, c, d) {
        for (var e = a.length, f = Z.h(a) ? a.split("") : a, g = 0; g < e; g++)if (g in f && c.call(d, f[g], g, a))return g;
        return -1
    };
    Z.f.Ie = function (a, c, d) {
        c = Z.f.Fc(a, c, d);
        return 0 > c ? null : Z.h(a) ? a.charAt(c) : a[c]
    };
    Z.f.Fc = function (a, c, d) {
        for (var e = Z.h(a) ? a.split("") : a, f = a.length - 1; 0 <= f; f--)if (f in e && c.call(d, e[f], f, a))return f;
        return -1
    };
    Z.f.contains = function (a, c) {
        return 0 <= Z.f.indexOf(a, c)
    };
    Z.f.Nc = function (a) {
        return 0 == a.length
    };
    Z.f.clear = function (a) {
        if (!Z.isArray(a))for (var c = a.length - 1; 0 <= c; c--)delete a[c];
        a.length = 0
    };
    Z.f.bf = function (a, c) {
        Z.f.contains(a, c) || a.push(c)
    };
    Z.f.tb = function (a, c, d) {
        Z.f.splice(a, d, 0, c)
    };
    Z.f.cf = function (a, c, d) {
        Z.cd(Z.f.splice, a, d, 0).apply(null, c)
    };
    Z.f.insertBefore = function (a, c, d) {
        var e;
        2 == arguments.length || 0 > (e = Z.f.indexOf(a, d)) ? a.push(c) : Z.f.tb(a, c, e)
    };
    Z.f.remove = function (a, c) {
        c = Z.f.indexOf(a, c);
        var d;
        (d = 0 <= c) && Z.f.L(a, c);
        return d
    };
    Z.f.Yf = function (a, c) {
        c = Z.f.lastIndexOf(a, c);
        return 0 <= c ? (Z.f.L(a, c), !0) : !1
    };
    Z.f.L = function (a, c) {
        return 1 == Array.prototype.splice.call(a, c, 1).length
    };
    Z.f.Xf = function (a, c, d) {
        c = Z.f.findIndex(a, c, d);
        return 0 <= c ? (Z.f.L(a, c), !0) : !1
    };
    Z.f.Uf = function (a, c, d) {
        var e = 0;
        Z.f.kb(a, function (f, g) {
            c.call(d, f, g, a) && Z.f.L(a, g) && e++
        });
        return e
    };
    Z.f.concat = function (a) {
        return Array.prototype.concat.apply(Array.prototype, arguments)
    };
    Z.f.join = function (a) {
        return Array.prototype.concat.apply(Array.prototype, arguments)
    };
    Z.f.pd = function (a) {
        var c = a.length;
        if (0 < c) {
            for (var d = Array(c), e = 0; e < c; e++)d[e] = a[e];
            return d
        }
        return []
    };
    Z.f.clone = Z.f.pd;
    Z.f.extend = function (a, c) {
        for (var d = 1; d < arguments.length; d++) {
            var e = arguments[d];
            if (Z.Ia(e)) {
                var f = a.length || 0, g = e.length || 0;
                a.length = f + g;
                for (var h = 0; h < g; h++)a[f + h] = e[h]
            } else a.push(e)
        }
    };
    Z.f.splice = function (a, c, d, e) {
        return Array.prototype.splice.apply(a, Z.f.slice(arguments, 1))
    };
    Z.f.slice = function (a, c, d) {
        return 2 >= arguments.length ? Array.prototype.slice.call(a, c) : Array.prototype.slice.call(a, c, d)
    };
    Z.f.Vf = function (a, c, d) {
        function e(a) {
            return Z.Z(a) ? "o" + Z.qb(a) : (typeof a).charAt(0) + a
        }

        c = c || a;
        d = d || e;
        for (var f = {}, g = 0, h = 0; h < a.length;) {
            var l = a[h++], m = d(l);
            Object.prototype.hasOwnProperty.call(f, m) || (f[m] = !0, c[g++] = l)
        }
        c.length = g
    };
    Z.f.cb = function (a, c, d) {
        return Z.f.eb(a, d || Z.f.C, !1, c)
    };
    Z.f.ge = function (a, c, d) {
        return Z.f.eb(a, c, !0, void 0, d)
    };
    Z.f.eb = function (a, c, d, e, f) {
        for (var g = 0, h = a.length, l; g < h;) {
            var m = g + h >> 1, n;
            n = d ? c.call(f, a[m], m, a) : c(e, a[m]);
            0 < n ? g = m + 1 : (h = m, l = !n)
        }
        return l ? g : ~g
    };
    Z.f.sort = function (a, c) {
        a.sort(c || Z.f.C)
    };
    Z.f.jg = function (a, c) {
        for (var d = Array(a.length), e = 0; e < a.length; e++)d[e] = {index: e, value: a[e]};
        var f = c || Z.f.C;
        Z.f.sort(d, function (a, c) {
            return f(a.value, c.value) || a.index - c.index
        });
        for (e = 0; e < a.length; e++)a[e] = d[e].value
    };
    Z.f.kd = function (a, c, d) {
        var e = d || Z.f.C;
        Z.f.sort(a, function (a, d) {
            return e(c(a), c(d))
        })
    };
    Z.f.gg = function (a, c, d) {
        Z.f.kd(a, function (a) {
            return a[c]
        }, d)
    };
    Z.f.sf = function (a, c, d) {
        c = c || Z.f.C;
        for (var e = 1; e < a.length; e++) {
            var f = c(a[e - 1], a[e]);
            if (0 < f || 0 == f && d)return !1
        }
        return !0
    };
    Z.f.Fe = function (a, c, d) {
        if (!Z.Ia(a) || !Z.Ia(c) || a.length != c.length)return !1;
        var e = a.length;
        d = d || Z.f.Bc;
        for (var f = 0; f < e; f++)if (!d(a[f], c[f]))return !1;
        return !0
    };
    Z.f.xe = function (a, c, d) {
        d = d || Z.f.C;
        for (var e = Math.min(a.length, c.length), f = 0; f < e; f++) {
            var g = d(a[f], c[f]);
            if (0 != g)return g
        }
        return Z.f.C(a.length, c.length)
    };
    Z.f.C = function (a, c) {
        return a > c ? 1 : a < c ? -1 : 0
    };
    Z.f.ef = function (a, c) {
        return -Z.f.C(a, c)
    };
    Z.f.Bc = function (a, c) {
        return a === c
    };
    Z.f.$d = function (a, c, d) {
        d = Z.f.cb(a, c, d);
        return 0 > d ? (Z.f.tb(a, c, -(d + 1)), !0) : !1
    };
    Z.f.fe = function (a, c, d) {
        c = Z.f.cb(a, c, d);
        return 0 <= c ? Z.f.L(a, c) : !1
    };
    Z.f.je = function (a, c, d) {
        for (var e = {}, f = 0; f < a.length; f++) {
            var g = a[f], h = c.call(d, g, f, a);
            Z.O(h) && (e[h] || (e[h] = [])).push(g)
        }
        return e
    };
    Z.f.wg = function (a, c, d) {
        var e = {};
        Z.f.forEach(a, function (f, g) {
            e[c.call(d, f, g, a)] = f
        });
        return e
    };
    Z.f.Mf = function (a, c, d) {
        var e = [], f = 0, g = a;
        d = d || 1;
        void 0 !== c && (f = a, g = c);
        if (0 > d * (g - f))return [];
        if (0 < d)for (a = f; a < g; a += d)e.push(a); else for (a = f; a > g; a += d)e.push(a);
        return e
    };
    Z.f.repeat = function (a, c) {
        for (var d = [], e = 0; e < c; e++)d[e] = a;
        return d
    };
    Z.f.Gc = function (a) {
        for (var c = [], d = 0; d < arguments.length; d++) {
            var e = arguments[d];
            if (Z.isArray(e))for (var f = 0; f < e.length; f += 8192)for (var g = Z.f.Gc.apply(null, Z.f.slice(e, f, f + 8192)), h = 0; h < g.length; h++)c.push(g[h]); else c.push(e)
        }
        return c
    };
    Z.f.rotate = function (a, c) {
        a.length && (c %= a.length, 0 < c ? Array.prototype.unshift.apply(a, a.splice(-c, c)) : 0 > c && Array.prototype.push.apply(a, a.splice(0, -c)));
        return a
    };
    Z.f.Cf = function (a, c, d) {
        c = Array.prototype.splice.call(a, c, 1);
        Array.prototype.splice.call(a, d, 0, c[0])
    };
    Z.f.Ig = function (a) {
        if (!arguments.length)return [];
        for (var c = [], d = arguments[0].length, e = 1; e < arguments.length; e++)arguments[e].length < d && (d = arguments[e].length);
        for (e = 0; e < d; e++) {
            for (var f = [], g = 0; g < arguments.length; g++)f.push(arguments[g][e]);
            c.push(f)
        }
        return c
    };
    Z.f.fg = function (a, c) {
        c = c || Math.random;
        for (var d = a.length - 1; 0 < d; d--) {
            var e = Math.floor(c() * (d + 1)), f = a[d];
            a[d] = a[e];
            a[e] = f
        }
    };
    Z.f.Ae = function (a, c) {
        var d = [];
        Z.f.forEach(c, function (c) {
            d.push(a[c])
        });
        return d
    };
    Z.f.ze = function (a, c, d) {
        return Z.f.concat.apply([], Z.f.map(a, c, d))
    };
    Z.locale = {};
    Z.locale.J = {
        COUNTRY: {
            AD: "Andorra",
            AE: gb,
            AF: U,
            AG: ca,
            AI: "Anguilla",
            AL: "Shqip\u00ebria",
            AM: fb,
            AN: pa,
            AO: "Angola",
            AQ: "Antarctica",
            AR: "Argentina",
            AS: b,
            AT: "\u00d6sterreich",
            AU: "Australia",
            AW: "Aruba",
            AX: "\u00c5land",
            AZ: "Az\u0259rbaycan",
            BA: p,
            BB: "Barbados",
            BD: "\u09ac\u09be\u0982\u09b2\u09be\u09a6\u09c7\u09b6",
            BE: "Belgi\u00eb",
            BF: "Burkina Faso",
            BG: "\u0411\u044a\u043b\u0433\u0430\u0440\u0438\u044f",
            BH: "\u0627\u0644\u0628\u062d\u0631\u064a\u0646",
            BI: "Burundi",
            BJ: "B\u00e9nin",
            BM: "Bermuda",
            BN: "Brunei",
            BO: k,
            BR: "Brasil",
            BS: "Bahamas",
            BT: "\u092d\u0942\u091f\u093e\u0928",
            BV: "Bouvet Island",
            BW: q,
            BY: cb,
            BZ: "Belize",
            CA: "Canada",
            CC: "Cocos (Keeling) Islands",
            CD: Ba,
            CF: Aa,
            CG: "Congo",
            CH: "Schweiz",
            CI: "C\u00f4te d\u2019Ivoire",
            CK: "Cook Islands",
            CL: "Chile",
            CM: "Cameroun",
            CN: "\u4e2d\u56fd",
            CO: "Colombia",
            CR: "Costa Rica",
            CS: Fa,
            CU: "Cuba",
            CV: "Cabo Verde",
            CX: fa,
            CY: "\u039a\u03cd\u03c0\u03c1\u03bf\u03c2",
            CZ: bb,
            DD: "East Germany",
            DE: "Deutschland",
            DJ: "Jabuuti",
            DK: "Danmark",
            DM: "Dominica",
            DO: ya,
            DZ: "\u0627\u0644\u062c\u0632\u0627\u0626\u0631",
            EC: "Ecuador",
            EE: "Eesti",
            EG: "\u0645\u0635\u0631",
            EH: hb,
            ER: "\u0627\u0631\u064a\u062a\u0631\u064a\u0627",
            ES: "Espa\u00f1a",
            ET: X,
            FI: "Suomi",
            FJ: "\u092b\u093f\u091c\u0940",
            FK: ga,
            FM: y,
            FO: "F\u00f8royar",
            FR: "France",
            FX: "Metropolitan France",
            GA: "Gabon",
            GB: Qa,
            GD: "Grenada",
            GE: "\u10e1\u10d0\u10e5\u10d0\u10e0\u10d7\u10d5\u10d4\u10da\u10dd",
            GF: ia,
            GG: "Guernsey",
            GH: t,
            GI: "Gibraltar",
            GL: ja,
            GM: "Gambia",
            GN: "Guin\u00e9e",
            GP: "Guadeloupe",
            GQ: ha,
            GR: "\u0395\u03bb\u03bb\u03ac\u03b4\u03b1",
            GS: "South Georgia and the South Sandwich Islands",
            GT: "Guatemala",
            GU: "Guam",
            GW: "Guin\u00e9 Bissau",
            GY: "Guyana",
            HK: "\u9999\u6e2f",
            HM: "Heard Island and McDonald Islands",
            HN: u,
            HR: "Hrvatska",
            HT: "Ha\u00efti",
            HU: "Magyarorsz\u00e1g",
            ID: v,
            IE: "Ireland",
            IL: "\u05d9\u05e9\u05e8\u05d0\u05dc",
            IM: "Isle of Man",
            IN: W,
            IO: "British Indian Ocean Territory",
            IQ: "\u0627\u0644\u0639\u0631\u0627\u0642",
            IR: "\u0627\u06cc\u0631\u0627\u0646",
            IS: "\u00cdsland",
            IT: "Italia",
            JE: "Jersey",
            JM: "Jamaica",
            JO: "\u0627\u0644\u0623\u0631\u062f\u0646",
            JP: "\u65e5\u672c",
            KE: "Kenya",
            KG: db,
            KH: "\u1780\u1798\u17d2\u1796\u17bb\u1787\u17b6",
            KI: x,
            KM: kb,
            KN: Ca,
            KP: lb,
            KR: "\ub300\ud55c\ubbfc\uad6d",
            KW: "\u0627\u0644\u0643\u0648\u064a\u062a",
            KY: ea,
            KZ: "\u041a\u0430\u0437\u0430\u0445\u0441\u0442\u0430\u043d",
            LA: "\u0e25\u0e32\u0e27",
            LB: "\u0644\u0628\u0646\u0627\u0646",
            LC: "Saint Lucia",
            LI: "Liechtenstein",
            LK: "\u0b87\u0bb2\u0b99\u0bcd\u0b95\u0bc8",
            LR: "Liberia",
            LS: "Lesotho",
            LT: "Lietuva",
            LU: la,
            LV: "Latvija",
            LY: "\u0644\u064a\u0628\u064a\u0627",
            MA: "\u0627\u0644\u0645\u063a\u0631\u0628",
            MC: "Monaco",
            MD: oa,
            ME: "\u0426\u0440\u043d\u0430 \u0413\u043e\u0440\u0430",
            MG: ma,
            MH: na,
            MK: "\u041c\u0430\u043a\u0435\u0434\u043e\u043d\u0438\u0458\u0430",
            ML: "\u0645\u0627\u0644\u064a",
            MM: "Myanmar",
            MN: "\u8499\u53e4",
            MO: "\u6fb3\u95e8",
            MP: sa,
            MQ: "Martinique",
            MR: "\u0645\u0648\u0631\u064a\u062a\u0627\u0646\u064a\u0627",
            MS: "Montserrat",
            MT: "Malta",
            MU: "Mauritius",
            MV: "Maldives",
            MW: "Malawi",
            MX: "M\u00e9xico",
            MY: "Malaysia",
            MZ: "Mo\u00e7ambique",
            NA: "Namibia",
            NC: ta,
            NE: "Niger",
            NF: ra,
            NG: z,
            NI: "Nicaragua",
            NL: "Nederland",
            NO: "Norge",
            NP: "\u0928\u0947\u092a\u093e\u0932",
            NR: "Nauru",
            NT: "Neutral Zone",
            NU: "Niue",
            NZ: qa,
            OM: "\u0639\u0645\u0627\u0646",
            PA: "Panam\u00e1",
            PE: "Per\u00fa",
            PF: wa,
            PG: A,
            PH: va,
            PK: V,
            PL: "Polska",
            PM: Ea,
            PN: "Pitcairn",
            PR: xa,
            PS: "\u0641\u0644\u0633\u0637\u064a\u0646",
            PT: "Portugal",
            PW: "Palau",
            PY: ua,
            QA: "\u0642\u0637\u0631",
            QO: "Outlying Oceania",
            QU: "European Union",
            RE: "R\u00e9union",
            RO: "Rom\u00e2nia",
            RS: "\u0421\u0440\u0431\u0438\u0458\u0430",
            RU: "\u0420\u043e\u0441\u0441\u0438\u044f",
            RW: B,
            SA: ib,
            SB: Ia,
            SC: Ga,
            SD: "\u0627\u0644\u0633\u0648\u062f\u0627\u0646",
            SE: "Sverige",
            SG: "\u65b0\u52a0\u5761",
            SH: "Saint Helena",
            SI: "Slovenija",
            SJ: Ja,
            SK: Ha,
            SL: "Sierra Leone",
            SM: "San Marino",
            SN: D,
            SO: "Somali",
            SR: "Suriname",
            ST: La,
            SU: "Union of Soviet Socialist Republics",
            SV: "El Salvador",
            SY: "\u0633\u0648\u0631\u064a\u0627",
            SZ: Ka,
            TC: Oa,
            TD: "\u062a\u0634\u0627\u062f",
            TF: "French Southern Territories",
            TG: "Togo",
            TH: "\u0e1b\u0e23\u0e30\u0e40\u0e17\u0e28\u0e44\u0e17\u0e22",
            TJ: "\u062a\u0627\u062c\u06cc\u06a9\u0633\u062a\u0627\u0646",
            TK: F,
            TL: Na,
            TM: "\u0422\u0443\u0440\u043a\u043c\u0435\u043d\u0438\u0441\u0442\u0430\u043d",
            TN: "\u062a\u0648\u0646\u0633",
            TO: "Tonga",
            TR: H,
            TT: "Trinidad y Tobago",
            TV: G,
            TW: "\u53f0\u6e7e",
            TZ: Ma,
            UA: "\u0423\u043a\u0440\u0430\u0457\u043d\u0430",
            UG: "Uganda",
            UM: Sa,
            US: Ra,
            UY: "Uruguay",
            UZ: "\u040e\u0437\u0431\u0435\u043a\u0438\u0441\u0442\u043e\u043d",
            VA: "Vaticano",
            VC: Da,
            VE: "Venezuela",
            VG: da,
            VI: Pa,
            VN: "Vi\u1ec7t Nam",
            VU: I,
            WF: Ua,
            WS: "Samoa",
            YD: "People's Democratic Republic of Yemen",
            YE: "\u0627\u0644\u064a\u0645\u0646",
            YT: "Mayotte",
            ZA: C,
            ZM: "Zambia",
            ZW: "Zimbabwe",
            ZZ: Ta,
            aa_DJ: "Jabuuti",
            aa_ER: "\u00c9rythr\u00e9e",
            aa_ER_SAAHO: "\u00c9rythr\u00e9e",
            aa_ET: w,
            af_NA: "Namibi\u00eb",
            af_ZA: "Suid-Afrika",
            ak_GH: t,
            am_ET: X,
            ar_AE: gb,
            ar_BH: "\u0627\u0644\u0628\u062d\u0631\u064a\u0646",
            ar_DJ: "\u062c\u064a\u0628\u0648\u062a\u064a",
            ar_DZ: "\u0627\u0644\u062c\u0632\u0627\u0626\u0631",
            ar_EG: "\u0645\u0635\u0631",
            ar_EH: hb,
            ar_ER: "\u0627\u0631\u064a\u062a\u0631\u064a\u0627",
            ar_IL: "\u0627\u0633\u0631\u0627\u0626\u064a\u0644",
            ar_IQ: "\u0627\u0644\u0639\u0631\u0627\u0642",
            ar_JO: "\u0627\u0644\u0623\u0631\u062f\u0646",
            ar_KM: kb,
            ar_KW: "\u0627\u0644\u0643\u0648\u064a\u062a",
            ar_LB: "\u0644\u0628\u0646\u0627\u0646",
            ar_LY: "\u0644\u064a\u0628\u064a\u0627",
            ar_MA: "\u0627\u0644\u0645\u063a\u0631\u0628",
            ar_MR: "\u0645\u0648\u0631\u064a\u062a\u0627\u0646\u064a\u0627",
            ar_OM: "\u0639\u0645\u0627\u0646",
            ar_PS: "\u0641\u0644\u0633\u0637\u064a\u0646",
            ar_QA: "\u0642\u0637\u0631",
            ar_SA: ib,
            ar_SD: "\u0627\u0644\u0633\u0648\u062f\u0627\u0646",
            ar_SY: "\u0633\u0648\u0631\u064a\u0627",
            ar_TD: "\u062a\u0634\u0627\u062f",
            ar_TN: "\u062a\u0648\u0646\u0633",
            ar_YE: "\u0627\u0644\u064a\u0645\u0646",
            as_IN: "\u09ad\u09be\u09f0\u09a4",
            ay_BO: k,
            az_AZ: "Az\u0259rbaycan",
            az_Cyrl_AZ: "\u0410\u0437\u04d9\u0440\u0431\u0430\u0458\u04b9\u0430\u043d",
            az_Latn_AZ: "Azerbaycan",
            be_BY: cb,
            bg_BG: "\u0411\u044a\u043b\u0433\u0430\u0440\u0438\u044f",
            bi_VU: I,
            bn_BD: "\u09ac\u09be\u0982\u09b2\u09be\u09a6\u09c7\u09b6",
            bn_IN: "\u09ad\u09be\u09b0\u09a4",
            bo_CN: "\u0f62\u0f92\u0fb1\u0f0b\u0f53\u0f42",
            bo_IN: "\u0f62\u0f92\u0fb1\u0f0b\u0f42\u0f62\u0f0b",
            bs_BA: p,
            byn_ER: "\u12a4\u122d\u1275\u122b",
            ca_AD: "Andorra",
            ca_ES: "Espanya",
            cch_NG: z,
            ch_GU: "Guam",
            chk_FM: y,
            cop_Arab_EG: "\u0645\u0635\u0631",
            cop_Arab_US: jb,
            cop_EG: "\u0645\u0635\u0631",
            cop_US: jb,
            cs_CZ: bb,
            cy_GB: "Prydain Fawr",
            da_DK: "Danmark",
            da_GL: "Gr\u00f8nland",
            de_AT: "\u00d6sterreich",
            de_BE: "Belgien",
            de_CH: "Schweiz",
            de_DE: "Deutschland",
            de_LI: "Liechtenstein",
            de_LU: "Luxemburg",
            dv_MV: "Maldives",
            dz_BT: "Bhutan",
            ee_GH: t,
            ee_TG: "Togo",
            efi_NG: z,
            el_CY: "\u039a\u03cd\u03c0\u03c1\u03bf\u03c2",
            el_GR: "\u0395\u03bb\u03bb\u03ac\u03b4\u03b1",
            en_AG: ca,
            en_AI: "Anguilla",
            en_AS: b,
            en_AU: "Australia",
            en_BB: "Barbados",
            en_BE: "Belgium",
            en_BM: "Bermuda",
            en_BS: "Bahamas",
            en_BW: q,
            en_BZ: "Belize",
            en_CA: "Canada",
            en_CC: "Cocos Islands",
            en_CK: "Cook Islands",
            en_CM: "Cameroon",
            en_CX: fa,
            en_DM: "Dominica",
            en_FJ: "Fiji",
            en_FK: ga,
            en_FM: y,
            en_GB: Qa,
            en_GD: "Grenada",
            en_GG: "Guernsey",
            en_GH: t,
            en_GI: "Gibraltar",
            en_GM: "Gambia",
            en_GU: "Guam",
            en_GY: "Guyana",
            en_HK: "Hong Kong",
            en_HN: u,
            en_IE: "Ireland",
            en_IM: "Isle of Man",
            en_IN: "India",
            en_JE: "Jersey",
            en_JM: "Jamaica",
            en_KE: "Kenya",
            en_KI: x,
            en_KN: Ca,
            en_KY: ea,
            en_LC: "Saint Lucia",
            en_LR: "Liberia",
            en_LS: "Lesotho",
            en_MH: na,
            en_MP: sa,
            en_MS: "Montserrat",
            en_MT: "Malta",
            en_MU: "Mauritius",
            en_MW: "Malawi",
            en_NA: "Namibia",
            en_NF: ra,
            en_NG: z,
            en_NR: "Nauru",
            en_NU: "Niue",
            en_NZ: qa,
            en_PG: A,
            en_PH: va,
            en_PK: "Pakistan",
            en_PN: "Pitcairn",
            en_PR: xa,
            en_RW: B,
            en_SB: Ia,
            en_SC: Ga,
            en_SG: "Singapore",
            en_SH: "Saint Helena",
            en_SL: "Sierra Leone",
            en_SZ: Ka,
            en_TC: Oa,
            en_TK: F,
            en_TO: "Tonga",
            en_TT: "Trinidad and Tobago",
            en_TV: G,
            en_TZ: Ma,
            en_UG: "Uganda",
            en_UM: Sa,
            en_US: Ra,
            en_US_POSIX: Ra,
            en_VC: Da,
            en_VG: da,
            en_VI: Pa,
            en_VU: I,
            en_WS: "Samoa",
            en_ZA: C,
            en_ZM: "Zambia",
            en_ZW: "Zimbabwe",
            es_AR: "Argentina",
            es_BO: k,
            es_CL: "Chile",
            es_CO: "Colombia",
            es_CR: "Costa Rica",
            es_CU: "Cuba",
            es_DO: ya,
            es_EC: "Ecuador",
            es_ES: "Espa\u00f1a",
            es_GQ: "Guinea Ecuatorial",
            es_GT: "Guatemala",
            es_HN: u,
            es_MX: "M\u00e9xico",
            es_NI: "Nicaragua",
            es_PA: "Panam\u00e1",
            es_PE: "Per\u00fa",
            es_PH: "Filipinas",
            es_PR: xa,
            es_PY: ua,
            es_SV: "El Salvador",
            es_US: "Estados Unidos",
            es_UY: "Uruguay",
            es_VE: "Venezuela",
            et_EE: "Eesti",
            eu_ES: "Espainia",
            fa_AF: U,
            fa_IR: "\u0627\u06cc\u0631\u0627\u0646",
            fi_FI: "Suomi",
            fil_PH: va,
            fj_FJ: "Fiji",
            fo_FO: "F\u00f8royar",
            fr_BE: "Belgique",
            fr_BF: "Burkina Faso",
            fr_BI: "Burundi",
            fr_BJ: "B\u00e9nin",
            fr_CA: "Canada",
            fr_CD: Ba,
            fr_CF: Aa,
            fr_CG: "Congo",
            fr_CH: "Suisse",
            fr_CI: "C\u00f4te d\u2019Ivoire",
            fr_CM: "Cameroun",
            fr_DJ: "Djibouti",
            fr_DZ: "Alg\u00e9rie",
            fr_FR: "France",
            fr_GA: "Gabon",
            fr_GF: ia,
            fr_GN: "Guin\u00e9e",
            fr_GP: "Guadeloupe",
            fr_GQ: ha,
            fr_HT: "Ha\u00efti",
            fr_KM: "Comores",
            fr_LU: la,
            fr_MA: "Maroc",
            fr_MC: "Monaco",
            fr_MG: ma,
            fr_ML: "Mali",
            fr_MQ: "Martinique",
            fr_MU: "Maurice",
            fr_NC: ta,
            fr_NE: "Niger",
            fr_PF: wa,
            fr_PM: Ea,
            fr_RE: "R\u00e9union",
            fr_RW: B,
            fr_SC: Ga,
            fr_SN: D,
            fr_SY: "Syrie",
            fr_TD: "Tchad",
            fr_TG: "Togo",
            fr_TN: "Tunisie",
            fr_VU: I,
            fr_WF: Ua,
            fr_YT: "Mayotte",
            fur_IT: "Italia",
            ga_IE: "\u00c9ire",
            gaa_GH: t,
            gez_ER: "\u12a4\u122d\u1275\u122b",
            gez_ET: X,
            gil_KI: x,
            gl_ES: "Espa\u00f1a",
            gn_PY: ua,
            gu_IN: "\u0aad\u0abe\u0ab0\u0aa4",
            gv_GB: za,
            ha_Arab_NG: "\u0646\u064a\u062c\u064a\u0631\u064a\u0627",
            ha_GH: "\u063a\u0627\u0646\u0627",
            ha_Latn_GH: t,
            ha_Latn_NE: "Niger",
            ha_Latn_NG: "Nig\u00e9ria",
            ha_NE: "\u0627\u0644\u0646\u064a\u062c\u0631",
            ha_NG: "\u0646\u064a\u062c\u064a\u0631\u064a\u0627",
            haw_US: "\u02bbAmelika Hui P\u016b \u02bbIa",
            he_IL: "\u05d9\u05e9\u05e8\u05d0\u05dc",
            hi_IN: W,
            ho_PG: A,
            hr_BA: p,
            hr_HR: "Hrvatska",
            ht_HT: "Ha\u00efti",
            hu_HU: "Magyarorsz\u00e1g",
            hy_AM: fb,
            hy_AM_REVISED: fb,
            id_ID: v,
            ig_NG: z,
            ii_CN: "\ua34f\ua1e9",
            is_IS: "\u00cdsland",
            it_CH: "Svizzera",
            it_IT: "Italia",
            it_SM: "San Marino",
            ja_JP: "\u65e5\u672c",
            ka_GE: "\u10e1\u10d0\u10e5\u10d0\u10e0\u10d7\u10d5\u10d4\u10da\u10dd",
            kaj_NG: z,
            kam_KE: "Kenya",
            kcg_NG: z,
            kfo_NG: "Nig\u00e9ria",
            kk_KZ: "\u049a\u0430\u0437\u0430\u049b\u0441\u0442\u0430\u043d",
            kl_GL: ja,
            km_KH: "\u1780\u1798\u17d2\u1796\u17bb\u1787\u17b6",
            kn_IN: "\u0cad\u0cbe\u0cb0\u0ca4",
            ko_KP: lb,
            ko_KR: "\ub300\ud55c\ubbfc\uad6d",
            kok_IN: W,
            kos_FM: y,
            kpe_GN: "Guin\u00e9e",
            kpe_LR: "Lib\u00e9ria",
            ks_IN: W,
            ku_IQ: "Irak",
            ku_IR: "\u0130ran",
            ku_Latn_IQ: "Irak",
            ku_Latn_IR: "\u0130ran",
            ku_Latn_SY: "Suriye",
            ku_Latn_TR: H,
            ku_SY: "Suriye",
            ku_TR: H,
            kw_GB: za,
            ky_Cyrl_KG: db,
            ky_KG: "K\u0131rg\u0131zistan",
            la_VA: "Vaticano",
            lb_LU: la,
            ln_CD: Ba,
            ln_CG: "Kongo",
            lo_LA: "Laos",
            lt_LT: "Lietuva",
            lv_LV: "Latvija",
            mg_MG: ma,
            mh_MH: na,
            mi_NZ: qa,
            mk_MK: "\u041c\u0430\u043a\u0435\u0434\u043e\u043d\u0438\u0458\u0430",
            ml_IN: "\u0d07\u0d28\u0d4d\u0d24\u0d4d\u0d2f",
            mn_Cyrl_MN: "\u041c\u043e\u043d\u0433\u043e\u043b\u0438\u044f",
            mn_MN: "\u041c\u043e\u043d\u0433\u043e\u043b\u0438\u044f",
            mr_IN: W,
            ms_BN: "Brunei",
            ms_MY: "Malaysia",
            ms_SG: "Singapura",
            mt_MT: "Malta",
            my_MM: "Myanmar",
            na_NR: "Nauru",
            nb_NO: "Norge",
            nb_SJ: Ja,
            ne_NP: "\u0928\u0947\u092a\u093e\u0932",
            niu_NU: "Niue",
            nl_AN: pa,
            nl_AW: "Aruba",
            nl_BE: "Belgi\u00eb",
            nl_NL: "Nederland",
            nl_SR: "Suriname",
            nn_NO: "Noreg",
            nr_ZA: C,
            nso_ZA: C,
            ny_MW: "Malawi",
            om_ET: w,
            om_KE: "Keeniyaa",
            or_IN: "\u0b2d\u0b3e\u0b30\u0b24",
            pa_Arab_PK: V,
            pa_Guru_IN: "\u0a2d\u0a3e\u0a30\u0a24",
            pa_IN: "\u0a2d\u0a3e\u0a30\u0a24",
            pa_PK: V,
            pap_AN: pa,
            pau_PW: "Palau",
            pl_PL: "Polska",
            pon_FM: y,
            ps_AF: U,
            pt_AO: "Angola",
            pt_BR: "Brasil",
            pt_CV: "Cabo Verde",
            pt_GW: "Guin\u00e9 Bissau",
            pt_MZ: "Mo\u00e7ambique",
            pt_PT: "Portugal",
            pt_ST: La,
            pt_TL: Na,
            qu_BO: k,
            qu_PE: "Per\u00fa",
            rm_CH: "Schweiz",
            rn_BI: "Burundi",
            ro_MD: oa,
            ro_RO: "Rom\u00e2nia",
            ru_BY: cb,
            ru_KG: db,
            ru_KZ: "\u041a\u0430\u0437\u0430\u0445\u0441\u0442\u0430\u043d",
            ru_RU: "\u0420\u043e\u0441\u0441\u0438\u044f",
            ru_UA: "\u0423\u043a\u0440\u0430\u0438\u043d\u0430",
            rw_RW: B,
            sa_IN: W,
            sd_Deva_IN: W,
            sd_IN: W,
            se_FI: "Finland",
            se_NO: "Norge",
            sg_CF: Aa,
            sh_BA: "Bosnia and Herzegovina",
            sh_CS: Fa,
            si_LK: "Sri Lanka",
            sid_ET: w,
            sk_SK: Ha,
            sl_SI: "Slovenija",
            sm_AS: b,
            sm_WS: "Samoa",
            so_DJ: "Jabuuti",
            so_ET: "Itoobiya",
            so_KE: "Kiiniya",
            so_SO: "Soomaaliya",
            sq_AL: "Shqip\u00ebria",
            sr_BA: "\u0411\u043e\u0441\u043d\u0430 \u0438 \u0425\u0435\u0440\u0446\u0435\u0433\u043e\u0432\u0438\u043d\u0430",
            sr_CS: "\u0421\u0440\u0431\u0438\u0458\u0430 \u0438 \u0426\u0440\u043d\u0430 \u0413\u043e\u0440\u0430",
            sr_Cyrl_BA: "\u0411\u043e\u0441\u043d\u0438\u044f",
            sr_Cyrl_CS: "\u0421\u0435\u0440\u0431\u0438\u044f \u0438 \u0427\u0435\u0440\u043d\u043e\u0433\u043e\u0440\u0438\u044f",
            sr_Cyrl_ME: "\u0427\u0435\u0440\u043d\u043e\u0433\u043e\u0440\u0438\u044f",
            sr_Cyrl_RS: "\u0421\u0435\u0440\u0431\u0438\u044f",
            sr_Latn_BA: p,
            sr_Latn_CS: "Srbija i Crna Gora",
            sr_Latn_ME: "Crna Gora",
            sr_Latn_RS: "Srbija",
            sr_ME: "\u0426\u0440\u043d\u0430 \u0413\u043e\u0440\u0430",
            sr_RS: "\u0421\u0440\u0431\u0438\u0458\u0430",
            ss_SZ: Ka,
            ss_ZA: C,
            st_LS: "Lesotho",
            st_ZA: C,
            su_ID: v,
            sv_AX: "\u00c5land",
            sv_FI: "Finland",
            sv_SE: "Sverige",
            sw_KE: "Kenya",
            sw_TZ: Ma,
            sw_UG: "Uganda",
            swb_KM: kb,
            syr_SY: "Syria",
            ta_IN: "\u0b87\u0ba8\u0bcd\u0ba4\u0bbf\u0baf\u0bbe",
            ta_LK: "\u0b87\u0bb2\u0b99\u0bcd\u0b95\u0bc8",
            ta_SG: "\u0b9a\u0bbf\u0b99\u0bcd\u0b95\u0baa\u0bcd\u0baa\u0bc2\u0bb0\u0bcd",
            te_IN: "\u0c2d\u0c3e\u0c30\u0c24 \u0c26\u0c47\u0c33\u0c02",
            tet_TL: Na,
            tg_Cyrl_TJ: "\u0422\u0430\u0434\u0436\u0438\u043a\u0438\u0441\u0442\u0430\u043d",
            tg_TJ: "\u062a\u0627\u062c\u06a9\u0633\u062a\u0627\u0646",
            th_TH: "\u0e1b\u0e23\u0e30\u0e40\u0e17\u0e28\u0e44\u0e17\u0e22",
            ti_ER: "\u12a4\u122d\u1275\u122b",
            ti_ET: X,
            tig_ER: "\u12a4\u122d\u1275\u122b",
            tk_TM: "\u062a\u0631\u06a9\u0645\u0646\u0633\u062a\u0627\u0646",
            tkl_TK: F,
            tn_BW: q,
            tn_ZA: C,
            to_TO: "Tonga",
            tpi_PG: A,
            tr_CY: "G\u00fcney K\u0131br\u0131s Rum Kesimi",
            tr_TR: H,
            ts_ZA: C,
            tt_RU: "\u0420\u043e\u0441\u0441\u0438\u044f",
            tvl_TV: G,
            ty_PF: wa,
            uk_UA: "\u0423\u043a\u0440\u0430\u0457\u043d\u0430",
            uli_FM: y,
            und_ZZ: Ta,
            ur_IN: "\u0628\u06be\u0627\u0631\u062a",
            ur_PK: V,
            uz_AF: "Afganistan",
            uz_Arab_AF: U,
            uz_Cyrl_UZ: "\u0423\u0437\u0431\u0435\u043a\u0438\u0441\u0442\u0430\u043d",
            uz_Latn_UZ: "O\u02bfzbekiston",
            uz_UZ: "\u040e\u0437\u0431\u0435\u043a\u0438\u0441\u0442\u043e\u043d",
            ve_ZA: C,
            vi_VN: "Vi\u1ec7t Nam",
            wal_ET: X,
            wo_Arab_SN: "\u0627\u0644\u0633\u0646\u063a\u0627\u0644",
            wo_Latn_SN: D,
            wo_SN: D,
            xh_ZA: C,
            yap_FM: y,
            yo_NG: z,
            zh_CN: "\u4e2d\u56fd",
            zh_HK: "\u9999\u6e2f",
            zh_Hans_CN: "\u4e2d\u56fd",
            zh_Hans_SG: "\u65b0\u52a0\u5761",
            zh_Hant_HK: "\u4e2d\u83ef\u4eba\u6c11\u5171\u548c\u570b\u9999\u6e2f\u7279\u5225\u884c\u653f\u5340",
            zh_Hant_MO: "\u6fb3\u9580",
            zh_Hant_TW: "\u81fa\u7063",
            zh_MO: "\u6fb3\u95e8",
            zh_SG: "\u65b0\u52a0\u5761",
            zh_TW: "\u53f0\u6e7e",
            zu_ZA: C
        }, LANGUAGE: {
            aa: "afar",
            ab: "\u0430\u0431\u0445\u0430\u0437\u0441\u043a\u0438\u0439",
            ace: "Aceh",
            ach: "Acoli",
            ada: "Adangme",
            ady: "\u0430\u0434\u044b\u0433\u0435\u0439\u0441\u043a\u0438\u0439",
            ae: "Avestan",
            af: "Afrikaans",
            afa: "Afro-Asiatic Language",
            afh: "Afrihili",
            ain: "Ainu",
            ak: "Akan",
            akk: "Akkadian",
            ale: "Aleut",
            alg: "Algonquian Language",
            alt: "Southern Altai",
            am: "\u12a0\u121b\u122d\u129b",
            an: "Aragonese",
            ang: "Old English",
            anp: "Angika",
            apa: "Apache Language",
            ar: "\u0627\u0644\u0639\u0631\u0628\u064a\u0629",
            arc: "Aramaic",
            arn: "Araucanian",
            arp: "Arapaho",
            art: "Artificial Language",
            arw: "Arawak",
            as: "\u0985\u09b8\u09ae\u09c0\u09af\u09bc\u09be",
            ast: "asturiano",
            ath: "Athapascan Language",
            aus: "Australian Language",
            av: "\u0430\u0432\u0430\u0440\u0441\u043a\u0438\u0439",
            awa: "Awadhi",
            ay: "aimara",
            az: "az\u0259rbaycanca",
            az_Arab: "\u062a\u0631\u06a9\u06cc \u0622\u0630\u0631\u0628\u0627\u06cc\u062c\u0627\u0646\u06cc",
            az_Cyrl: "\u0410\u0437\u04d9\u0440\u0431\u0430\u0458\u04b9\u0430\u043d",
            az_Latn: "Azerice",
            ba: "\u0431\u0430\u0448\u043a\u0438\u0440\u0441\u043a\u0438\u0439",
            bad: "Banda",
            bai: "Bamileke Language",
            bal: "\u0628\u0644\u0648\u0686\u06cc",
            ban: "Balin",
            bas: "Basa",
            bat: "Baltic Language",
            be: "\u0431\u0435\u043b\u0430\u0440\u0443\u0441\u043a\u0430\u044f",
            bej: "Beja",
            bem: "Bemba",
            ber: "Berber",
            bg: "\u0431\u044a\u043b\u0433\u0430\u0440\u0441\u043a\u0438",
            bh: "\u092c\u093f\u0939\u093e\u0930\u0940",
            bho: "Bhojpuri",
            bi: "bichelamar ; bislama",
            bik: "Bikol",
            bin: "Bini",
            bla: "Siksika",
            bm: "bambara",
            bn: "\u09ac\u09be\u0982\u09b2\u09be",
            bnt: "Bantu",
            bo: "\u0f54\u0f7c\u0f51\u0f0b\u0f66\u0f90\u0f51\u0f0b",
            br: "breton",
            bra: "Braj",
            bs: "Bosanski",
            btk: "Batak",
            bua: "Buriat",
            bug: "Bugis",
            byn: "\u1265\u120a\u1295",
            ca: "catal\u00e0",
            cad: "Caddo",
            cai: "Central American Indian Language",
            car: "Carib",
            cau: "Caucasian Language",
            cch: "Atsam",
            ce: "\u0447\u0435\u0447\u0435\u043d\u0441\u043a\u0438\u0439",
            ceb: "Cebuano",
            cel: "Celtic Language",
            ch: "Chamorro",
            chb: "Chibcha",
            chg: "Chagatai",
            chk: "Chuukese",
            chm: "\u043c\u0430\u0440\u0438\u0439\u0441\u043a\u0438\u0439 (\u0447\u0435\u0440\u0435\u043c\u0438\u0441\u0441\u043a\u0438\u0439)",
            chn: "Chinook Jargon",
            cho: "Choctaw",
            chp: "Chipewyan",
            chr: "Cherokee",
            chy: "Cheyenne",
            cmc: "Chamic Language",
            co: "corse",
            cop: "\u0642\u0628\u0637\u064a\u0629",
            cop_Arab: "\u0642\u0628\u0637\u064a\u0629",
            cpe: "English-based Creole or Pidgin",
            cpf: "French-based Creole or Pidgin",
            cpp: "Portuguese-based Creole or Pidgin",
            cr: "Cree",
            crh: "Crimean Turkish",
            crp: "Creole or Pidgin",
            cs: "\u010de\u0161tina",
            csb: "Kashubian",
            cu: "Church Slavic",
            cus: "Cushitic Language",
            cv: "\u0447\u0443\u0432\u0430\u0448\u0441\u043a\u0438\u0439",
            cy: "Cymraeg",
            da: "dansk",
            dak: "Dakota",
            dar: "\u0434\u0430\u0440\u0433\u0432\u0430",
            day: "Dayak",
            de: "Deutsch",
            del: "Delaware",
            den: "Slave",
            dgr: "Dogrib",
            din: "Dinka",
            doi: "\u0627\u0644\u062f\u0648\u062c\u0631\u0649",
            dra: "Dravidian Language",
            dsb: "Lower Sorbian",
            dua: "Duala",
            dum: "Middle Dutch",
            dv: "Divehi",
            dyu: "dioula",
            dz: "\u0f62\u0fab\u0f7c\u0f44\u0f0b\u0f41",
            ee: "Ewe",
            efi: "Efik",
            egy: "Ancient Egyptian",
            eka: "Ekajuk",
            el: "\u0395\u03bb\u03bb\u03b7\u03bd\u03b9\u03ba\u03ac",
            elx: "Elamite",
            en: "English",
            enm: "Middle English",
            eo: "esperanto",
            es: "espa\u00f1ol",
            et: "eesti",
            eu: "euskara",
            ewo: "Ewondo",
            fa: "\u0641\u0627\u0631\u0633\u06cc",
            fan: "fang",
            fat: "Fanti",
            ff: "Fulah",
            fi: "suomi",
            fil: "Filipino",
            fiu: "Finno-Ugrian Language",
            fj: "Fijian",
            fo: "f\u00f8royskt",
            fon: "Fon",
            fr: "fran\u00e7ais",
            frm: "Middle French",
            fro: "Old French",
            frr: "Northern Frisian",
            frs: "Eastern Frisian",
            fur: "friulano",
            fy: "Fries",
            ga: "Gaeilge",
            gaa: "Ga",
            gay: "Gayo",
            gba: "Gbaya",
            gd: "Scottish Gaelic",
            gem: "Germanic Language",
            gez: "\u130d\u12d5\u12dd\u129b",
            gil: "Gilbertese",
            gl: "galego",
            gmh: "Middle High German",
            gn: "guaran\u00ed",
            goh: "Old High German",
            gon: "Gondi",
            gor: "Gorontalo",
            got: "Gothic",
            grb: "Grebo",
            grc: "\u0391\u03c1\u03c7\u03b1\u03af\u03b1 \u0395\u03bb\u03bb\u03b7\u03bd\u03b9\u03ba\u03ac",
            gsw: "Schweizerdeutsch",
            gu: "\u0a97\u0ac1\u0a9c\u0ab0\u0abe\u0aa4\u0ac0",
            gv: "Gaelg",
            gwi: "Gwich\u02bcin",
            ha: "\u0627\u0644\u0647\u0648\u0633\u0627",
            ha_Arab: "\u0627\u0644\u0647\u0648\u0633\u0627",
            ha_Latn: "haoussa",
            hai: "Haida",
            haw: "\u02bb\u014dlelo Hawai\u02bbi",
            he: "\u05e2\u05d1\u05e8\u05d9\u05ea",
            hi: "\u0939\u093f\u0902\u0926\u0940",
            hil: "Hiligaynon",
            him: "Himachali",
            hit: "Hittite",
            hmn: "Hmong",
            ho: "Hiri Motu",
            hr: "hrvatski",
            hsb: "Upper Sorbian",
            ht: "ha\u00eftien",
            hu: "magyar",
            hup: "Hupa",
            hy: "\u0540\u0561\u0575\u0565\u0580\u0567\u0576",
            hz: "Herero",
            ia: "interlingvao",
            iba: "Iban",
            id: "Bahasa Indonesia",
            ie: "Interlingue",
            ig: "Igbo",
            ii: "\ua188\ua320\ua259",
            ijo: "Ijo",
            ik: "Inupiaq",
            ilo: "Iloko",
            inc: "Indic Language",
            ine: "Indo-European Language",
            inh: "\u0438\u043d\u0433\u0443\u0448\u0441\u043a\u0438\u0439",
            io: "Ido",
            ira: "Iranian Language",
            iro: "Iroquoian Language",
            is: "\u00edslenska",
            it: "italiano",
            iu: "Inuktitut",
            ja: "\u65e5\u672c\u8a9e",
            jbo: "Lojban",
            jpr: "Judeo-Persian",
            jrb: "Judeo-Arabic",
            jv: "Jawa",
            ka: "\u10e5\u10d0\u10e0\u10d7\u10e3\u10da\u10d8",
            kaa: "\u043a\u0430\u0440\u0430\u043a\u0430\u043b\u043f\u0430\u043a\u0441\u043a\u0438\u0439",
            kab: "kabyle",
            kac: "Kachin",
            kaj: "Jju",
            kam: "Kamba",
            kar: "Karen",
            kaw: "Kawi",
            kbd: "\u043a\u0430\u0431\u0430\u0440\u0434\u0438\u043d\u0441\u043a\u0438\u0439",
            kcg: "Tyap",
            kfo: "koro",
            kg: "Kongo",
            kha: "Khasi",
            khi: "Khoisan Language",
            kho: "Khotanese",
            ki: "Kikuyu",
            kj: "Kuanyama",
            kk: "\u049a\u0430\u0437\u0430\u049b",
            kl: "kalaallisut",
            km: "\u1797\u17b6\u179f\u17b6\u1781\u17d2\u1798\u17c2\u179a",
            kmb: "quimbundo",
            kn: "\u0c95\u0ca8\u0ccd\u0ca8\u0ca1",
            ko: "\ud55c\uad6d\uc5b4",
            kok: "\u0915\u094b\u0902\u0915\u0923\u0940",
            kos: "Kosraean",
            kpe: "kpell\u00e9",
            kr: "Kanuri",
            krc: "\u043a\u0430\u0440\u0430\u0447\u0430\u0435\u0432\u043e-\u0431\u0430\u043b\u043a\u0430\u0440\u0441\u043a\u0438\u0439",
            krl: "\u043a\u0430\u0440\u0435\u043b\u044c\u0441\u043a\u0438\u0439",
            kro: "Kru",
            kru: "Kurukh",
            ks: "\u0915\u093e\u0936\u094d\u092e\u093f\u0930\u0940",
            ku: "K\u00fcrt\u00e7e",
            ku_Arab: "\u0627\u0644\u0643\u0631\u062f\u064a\u0629",
            ku_Latn: "K\u00fcrt\u00e7e",
            kum: "\u043a\u0443\u043c\u044b\u043a\u0441\u043a\u0438\u0439",
            kut: "Kutenai",
            kv: "Komi",
            kw: "kernewek",
            ky: "K\u0131rg\u0131zca",
            ky_Arab: "\u0627\u0644\u0642\u064a\u0631\u063a\u0633\u062a\u0627\u0646\u064a\u0629",
            ky_Cyrl: "\u043a\u0438\u0440\u0433\u0438\u0437\u0441\u043a\u0438\u0439",
            la: "latino",
            lad: "\u05dc\u05d3\u05d9\u05e0\u05d5",
            lah: "\u0644\u0627\u0647\u0646\u062f\u0627",
            lam: "Lamba",
            lb: "luxembourgeois",
            lez: "\u043b\u0435\u0437\u0433\u0438\u043d\u0441\u043a\u0438\u0439",
            lg: "Ganda",
            li: "Limburgs",
            ln: "lingala",
            lo: "Lao",
            lol: "mongo",
            loz: "Lozi",
            lt: "lietuvi\u0173",
            lu: "luba-katanga",
            lua: "luba-lulua",
            lui: "Luiseno",
            lun: "Lunda",
            luo: "Luo",
            lus: "Lushai",
            lv: "latvie\u0161u",
            mad: "Madura",
            mag: "Magahi",
            mai: "Maithili",
            mak: "Makassar",
            man: "Mandingo",
            map: "Austronesian",
            mas: "Masai",
            mdf: "\u043c\u043e\u043a\u0448\u0430",
            mdr: "Mandar",
            men: "Mende",
            mg: "malgache",
            mga: "Middle Irish",
            mh: "Marshallese",
            mi: "Maori",
            mic: "Micmac",
            min: "Minangkabau",
            mis: "Miscellaneous Language",
            mk: "\u043c\u0430\u043a\u0435\u0434\u043e\u043d\u0441\u043a\u0438",
            mkh: "Mon-Khmer Language",
            ml: "\u0d2e\u0d32\u0d2f\u0d3e\u0d33\u0d02",
            mn: eb,
            mn_Cyrl: eb,
            mn_Mong: eb,
            mnc: "Manchu",
            mni: "Manipuri",
            mno: "Manobo Language",
            mo: "Moldavian",
            moh: "Mohawk",
            mos: "mor\u00e9 ; mossi",
            mr: "\u092e\u0930\u093e\u0920\u0940",
            ms: "Bahasa Melayu",
            mt: "Malti",
            mul: "Multiple Languages",
            mun: "Munda Language",
            mus: "Creek",
            mwl: "Mirandese",
            mwr: "Marwari",
            my: "Burmese",
            myn: "Mayan Language",
            myv: "\u044d\u0440\u0437\u044f",
            na: "Nauru",
            nah: "Nahuatl",
            nai: "North American Indian Language",
            nap: "napoletano",
            nb: "norsk bokm\u00e5l",
            nd: "North Ndebele",
            nds: "Low German",
            ne: "\u0928\u0947\u092a\u093e\u0932\u0940",
            "new": "Newari",
            ng: "Ndonga",
            nia: "Nias",
            nic: "Niger-Kordofanian Language",
            niu: "Niuean",
            nl: "Nederlands",
            nn: "nynorsk",
            no: "Norwegian",
            nog: "\u043d\u043e\u0433\u0430\u0439\u0441\u043a\u0438\u0439",
            non: "Old Norse",
            nqo: "N\u2019Ko",
            nr: "South Ndebele",
            nso: "Northern Sotho",
            nub: "Nubian Language",
            nv: "Navajo",
            nwc: "Classical Newari",
            ny: "nianja; chicheua; cheua",
            nym: "Nyamwezi",
            nyn: "Nyankole",
            nyo: "Nyoro",
            nzi: "Nzima",
            oc: "occitan",
            oj: "Ojibwa",
            om: "Oromoo",
            or: "\u0b13\u0b21\u0b3c\u0b3f\u0b06",
            os: "\u043e\u0441\u0435\u0442\u0438\u043d\u0441\u043a\u0438\u0439",
            osa: "Osage",
            ota: "Ottoman Turkish",
            oto: "Otomian Language",
            pa: "\u0a2a\u0a70\u0a1c\u0a3e\u0a2c\u0a40",
            pa_Arab: "\u067e\u0646\u062c\u0627\u0628",
            pa_Guru: "\u0a2a\u0a70\u0a1c\u0a3e\u0a2c\u0a40",
            paa: "Papuan Language",
            pag: "Pangasinan",
            pal: "Pahlavi",
            pam: "Pampanga",
            pap: "Papiamento",
            pau: "Palauan",
            peo: "Old Persian",
            phi: "Philippine Language",
            phn: "Phoenician",
            pi: "\u0e1a\u0e32\u0e25\u0e35",
            pl: "polski",
            pon: "Pohnpeian",
            pra: "Prakrit Language",
            pro: "Old Proven\u00e7al",
            ps: "\u067e\u069a\u062a\u0648",
            pt: "portugu\u00eas",
            qu: "quechua",
            raj: "Rajasthani",
            rap: "Rapanui",
            rar: "Rarotongan",
            rm: "R\u00e4toromanisch",
            rn: "roundi",
            ro: "rom\u00e2n\u0103",
            roa: "Romance Language",
            rom: "Romany",
            ru: "\u0440\u0443\u0441\u0441\u043a\u0438\u0439",
            rup: "Aromanian",
            rw: "rwanda",
            sa: "\u0938\u0902\u0938\u094d\u0915\u0943\u0924 \u092d\u093e\u0937\u093e",
            sad: "Sandawe",
            sah: "\u044f\u043a\u0443\u0442\u0441\u043a\u0438\u0439",
            sai: "South American Indian Language",
            sal: "Salishan Language",
            sam: "\u05d0\u05e8\u05de\u05d9\u05ea \u05e9\u05d5\u05de\u05e8\u05d5\u05e0\u05d9\u05ea",
            sas: "Sasak",
            sat: "Santali",
            sc: "Sardinian",
            scn: "siciliano",
            sco: "Scots",
            sd: "\u0938\u093f\u0928\u094d\u0927\u0940",
            sd_Arab: "\u0633\u0646\u062f\u06cc",
            sd_Deva: "\u0938\u093f\u0928\u094d\u0927\u0940",
            se: "nordsamiska",
            sel: "\u0441\u0435\u043b\u044c\u043a\u0443\u043f\u0441\u043a\u0438\u0439",
            sem: "Semitic Language",
            sg: "sangho",
            sga: "Old Irish",
            sgn: "Sign Language",
            sh: "Serbo-Croatian",
            shn: "Shan",
            si: "Sinhalese",
            sid: "Sidamo",
            sio: "Siouan Language",
            sit: "Sino-Tibetan Language",
            sk: "slovensk\u00fd",
            sl: "sloven\u0161\u010dina",
            sla: "Slavic Language",
            sm: "Samoan",
            sma: "sydsamiska",
            smi: "Sami Language",
            smj: "lulesamiska",
            smn: "Inari Sami",
            sms: "Skolt Sami",
            sn: "Shona",
            snk: "sonink\u00e9",
            so: "Soomaali",
            sog: "Sogdien",
            son: "Songhai",
            sq: "shqipe",
            sr: "\u0421\u0440\u043f\u0441\u043a\u0438",
            sr_Cyrl: "\u0441\u0435\u0440\u0431\u0441\u043a\u0438\u0439",
            sr_Latn: "Srpski",
            srn: "Sranantongo",
            srr: "s\u00e9r\u00e8re",
            ss: "Swati",
            ssa: "Nilo-Saharan Language",
            st: "Sesotho",
            su: "Sundan",
            suk: "Sukuma",
            sus: "soussou",
            sux: "Sumerian",
            sv: "svenska",
            sw: "Kiswahili",
            syc: "Classical Syriac",
            syr: "Syriac",
            ta: "\u0ba4\u0bae\u0bbf\u0bb4\u0bcd",
            tai: "Tai Language",
            te: "\u0c24\u0c46\u0c32\u0c41\u0c17\u0c41",
            tem: "Timne",
            ter: "Tereno",
            tet: "t\u00e9tum",
            tg: "\u062a\u0627\u062c\u06a9",
            tg_Arab: "\u062a\u0627\u062c\u06a9",
            tg_Cyrl: "\u0442\u0430\u0434\u0436\u0438\u043a\u0441\u043a\u0438\u0439",
            th: "\u0e44\u0e17\u0e22",
            ti: "\u1275\u130d\u122d\u129b",
            tig: "\u1275\u130d\u1228",
            tiv: "Tiv",
            tk: "\u062a\u0631\u06a9\u0645\u0646\u06cc",
            tkl: F,
            tl: "Tagalog",
            tlh: "Klingon",
            tli: "Tlingit",
            tmh: "tamacheq",
            tn: "Tswana",
            to: "Tonga",
            tog: "Nyasa Tonga",
            tpi: "Tok Pisin",
            tr: "T\u00fcrk\u00e7e",
            ts: "Tsonga",
            tsi: "Tsimshian",
            tt: "\u0442\u0430\u0442\u0430\u0440\u0441\u043a\u0438\u0439",
            tum: "Tumbuka",
            tup: "Tupi Language",
            tut: "\u0430\u043b\u0442\u0430\u0439\u0441\u043a\u0438\u0435 (\u0434\u0440\u0443\u0433\u0438\u0435)",
            tvl: G,
            tw: "Twi",
            ty: "tahitien",
            tyv: "\u0442\u0443\u0432\u0438\u043d\u0441\u043a\u0438\u0439",
            udm: "\u0443\u0434\u043c\u0443\u0440\u0442\u0441\u043a\u0438\u0439",
            ug: "\u0443\u0439\u0433\u0443\u0440\u0441\u043a\u0438\u0439",
            uga: "Ugaritic",
            uk: "\u0443\u043a\u0440\u0430\u0457\u043d\u0441\u044c\u043a\u0430",
            umb: "umbundu",
            und: "English",
            ur: "\u0627\u0631\u062f\u0648",
            uz: "\u040e\u0437\u0431\u0435\u043a",
            uz_Arab: "\u0627\u06c9\u0632\u0628\u06d0\u06a9",
            uz_Cyrl: "\u0443\u0437\u0431\u0435\u043a\u0441\u043a\u0438\u0439",
            uz_Latn: "o'zbekcha",
            vai: "Vai",
            ve: "Venda",
            vi: "Ti\u1ebfng Vi\u1ec7t",
            vo: "volapuko",
            vot: "Votic",
            wa: "Wallonisch",
            wak: "Wakashan Language",
            wal: "Walamo",
            war: "Waray",
            was: "Washo",
            wen: "Sorbian Language",
            wo: "wolof",
            wo_Arab: "\u0627\u0644\u0648\u0644\u0648\u0641",
            wo_Latn: "wolof",
            xal: "\u043a\u0430\u043b\u043c\u044b\u0446\u043a\u0438\u0439",
            xh: "Xhosa",
            yao: "iao",
            yap: "Yapese",
            yi: "\u05d9\u05d9\u05d3\u05d9\u05e9",
            yo: "Yoruba",
            ypk: "Yupik Language",
            za: "Zhuang",
            zap: "Zapotec",
            zen: "Zenaga",
            zh: "\u4e2d\u6587",
            zh_Hans: "\u4e2d\u6587",
            zh_Hant: "\u4e2d\u6587",
            znd: "Zande",
            zu: "Zulu",
            zun: "Zuni",
            zxx: "No linguistic content",
            zza: "Zaza"
        }
    };
    Z.locale.dg = function (a) {
        a = a.replace(/-/g, "_");
        Z.locale.N = a
    };
    Z.locale.Da = function () {
        Z.locale.N || (Z.locale.N = "en");
        return Z.locale.N
    };
    Z.locale.H = {
        Cd: "DateTimeConstants",
        Ld: "NumberFormatConstants",
        hc: "TimeZoneConstants",
        Xb: ka,
        ic: "TimeZoneSelectedIds",
        kc: "TimeZoneSelectedShortNames",
        jc: "TimeZoneSelectedLongNames",
        gc: "TimeZoneAllLongNames"
    };
    Z.locale.Ca = function (a) {
        return (a = a.match(/^\w{2,3}([-_]|$)/)) ? a[0].replace(/[_-]/g, "") : ""
    };
    Z.locale.mb = function (a) {
        return (a = a.match(/[-_]([a-zA-Z]{2}|\d{3})([-_]|$)/)) ? a[0].replace(/[_-]/g, "") : ""
    };
    Z.locale.We = function (a) {
        a = a.split(/[-_]/g);
        return 1 < a.length && a[1].match(/^[a-zA-Z]{4}$/) ? a[1] : ""
    };
    Z.locale.Xe = function (a) {
        return (a = a.match(/[-_]([a-z]{2,})/)) ? a[1] : ""
    };
    Z.locale.Se = function (a) {
        var c = Z.locale.Ca(a) + "_" + Z.locale.mb(a);
        return c in Z.locale.J.COUNTRY ? Z.locale.J.COUNTRY[c] : a
    };
    Z.locale.Oe = function (a, c) {
        c || (c = Z.locale.ob());
        var d = Z.locale.mb(a);
        return d in c.COUNTRY ? c.COUNTRY[d] : a
    };
    Z.locale.Te = function (a) {
        if (a in Z.locale.J.LANGUAGE)return Z.locale.J.LANGUAGE[a];
        var c = Z.locale.Ca(a);
        return c in Z.locale.J.LANGUAGE ? Z.locale.J.LANGUAGE[c] : a
    };
    Z.locale.Pe = function (a, c) {
        c || (c = Z.locale.ob());
        if (a in c.LANGUAGE)return c.LANGUAGE[a];
        var d = Z.locale.Ca(a);
        return d in c.LANGUAGE ? c.LANGUAGE[d] : a
    };
    Z.locale.K = function (a, c, d) {
        Z.locale.m[c] || (Z.locale.m[c] = {});
        Z.locale.m[c][d] = a;
        Z.locale.N || (Z.locale.N = d)
    };
    Z.locale.rf = function (a, c) {
        return a in Z.locale.m && c in Z.locale.m[a]
    };
    Z.locale.m = {};
    Z.locale.Pf = function (a, c) {
        Z.locale.K(a, Z.locale.H.hc, c)
    };
    Z.locale.Nf = function (a, c) {
        Z.locale.K(a, Z.locale.H.Xb, c)
    };
    Z.locale.Qf = function (a, c) {
        Z.locale.K(a, Z.locale.H.ic, c)
    };
    Z.locale.Sf = function (a, c) {
        Z.locale.K(a, Z.locale.H.kc, c)
    };
    Z.locale.Rf = function (a, c) {
        Z.locale.K(a, Z.locale.H.jc, c)
    };
    Z.locale.Of = function (a, c) {
        Z.locale.K(a, Z.locale.H.gc, c)
    };
    Z.locale.ob = function () {
        var a = Z.locale.Da(), a = a ? a : Z.locale.Da();
        return ka in Z.locale.m ? Z.locale.m.LocaleNameConstants[a] : void 0
    };
    Z.locale.Ve = function (a, c) {
        c = c ? c : Z.locale.Da();
        if (a in Z.locale.m) {
            if (c in Z.locale.m[a])return Z.locale.m[a][c];
            c = c.split("_");
            return 1 < c.length && c[0]in Z.locale.m[a] ? Z.locale.m[a][c[0]] : Z.locale.m[a].en
        }
    };
    var google = {a: {}};
    google.a.c = {};
    google.a.c.languages = {
        af: !0,
        am: !0,
        az: !0,
        ar: !0,
        arb: "ar",
        bg: !0,
        bn: !0,
        ca: !0,
        cs: !0,
        cmn: "zh",
        da: !0,
        de: !0,
        el: !0,
        en: !0,
        en_gb: !0,
        es: !0,
        es_419: !0,
        et: !0,
        eu: !0,
        fa: !0,
        fi: !0,
        fil: !0,
        fr: !0,
        fr_ca: !0,
        gl: !0,
        ka: !0,
        gu: !0,
        he: "iw",
        hi: !0,
        hr: !0,
        hu: !0,
        hy: !0,
        id: !0,
        "in": "id",
        is: !0,
        it: !0,
        iw: !0,
        ja: !0,
        ji: "yi",
        jv: !1,
        jw: "jv",
        km: !0,
        kn: !0,
        ko: !0,
        lo: !0,
        lt: !0,
        lv: !0,
        ml: !0,
        mn: !0,
        mo: "ro",
        mr: !0,
        ms: !0,
        nb: "no",
        ne: !0,
        nl: !0,
        no: !0,
        pl: !0,
        pt: "pt_br",
        pt_br: !0,
        pt_pt: !0,
        ro: !0,
        ru: !0,
        si: !0,
        sk: !0,
        sl: !0,
        sr: !0,
        sv: !0,
        sw: !0,
        swh: "sw",
        ta: !0,
        te: !0,
        th: !0,
        tl: "fil",
        tr: !0,
        uk: !0,
        ur: !0,
        vi: !0,
        yi: !1,
        zh: "zh_cn",
        zh_cn: !0,
        zh_hk: !0,
        zh_tw: !0,
        zsm: "ms",
        zu: !0
    };
    google.a.c.P = {};
    google.a.c.I = "unknown";
    google.a.c.log = Y();
    google.a.c.error = Y();
    google.a.c.Y = !1;
    google.a.c.window = window;
    google.a.c.ud = {
        1: "1.0",
        "1.0": "current",
        "1.1": "upcoming",
        current: "44",
        upcoming: "45",
        41: "top",
        42: "top",
        43: "top",
        44: "top"
    };
    google.a.c.Ta = {
        gstatic: {
            prefix: "https://www.gstatic.com/charts",
            debug: "{prefix}/debug/{version}/jsapi_debug_{package}_module.js",
            compiled: "{prefix}/{version}/js/jsapi_compiled_{package}_module.js",
            i18n: "{prefix}/{version}/i18n/jsapi_compiled_i18n_{package}_module__{language}.js",
            css_prefix: "{prefix}/{version}/css",
            css: Ya,
            css_debug: Ya,
            third_party: $a,
            third_party_gen: $a
        }
    };
    google.a.c.i = google.a.c.Ta.gstatic;
    google.a.c.Cc = {
        format: [],
        "default": ["format"],
        ui: ["default"],
        ui_base: ["default"],
        annotatedtimeline: [R],
        annotationchart: [R, "controls", M, "table"],
        areachart: [R, K],
        bar: [R, N, T],
        barchart: [R, K],
        browserchart: [R],
        calendar: [R],
        charteditor: [R, M, P, Va, "gauge", "motionchart", "orgchart", "table"],
        charteditor_base: [S, M, P, Va, "gauge", "motionchart", "orgchart", "table_base"],
        columnchart: [R, K],
        controls: [R],
        controls_base: [S],
        corechart: [R],
        gantt: [R, N],
        gauge: [R],
        geochart: [R],
        geomap: [R],
        geomap_base: [S],
        helloworld: [R],
        imageareachart: [R,
            P],
        imagebarchart: [R, P],
        imagelinechart: [R, P],
        imagechart: [R],
        imagepiechart: [R, P],
        imagesparkline: [R, P],
        intensitymap: [R],
        line: [R, N, T],
        linechart: [R, K],
        map: [R],
        motionchart: [R],
        orgchart: [R],
        overtimecharts: [R, M],
        piechart: [R, K],
        sankey: ["d3", "d3.sankey", R],
        scatter: [R, N, T],
        scatterchart: [R, K],
        table: [R],
        table_base: [S],
        timeline: [R, N],
        treemap: [R],
        wordtree: [R]
    };
    google.a.c.od = {d3: "d3/d3.js", "d3.sankey": "d3/d3.sankey.js", webfontloader: "webfontloader/webfont.js"};
    google.a.c.Eb = {dygraph: "dygraphs/dygraph-tickers-combined.js"};
    google.a.c.zc = {
        annotatedtimeline: "/annotatedtimeline/annotatedtimeline.css",
        annotationchart: "annotationchart/annotationchart.css",
        charteditor: "charteditor/charteditor.css",
        charteditor_base: "charteditor/charteditor_base.css",
        controls: "controls/controls.css",
        imagesparkline: "imagesparkline/imagesparkline.css",
        intensitymap: "intensitymap/intensitymap.css",
        orgchart: "orgchart/orgchart.css",
        table: "table/table.css",
        table_base: "table/table_base.css",
        ui: ["util/util.css", "core/tooltip.css"],
        ui_base: "util/util_base.css"
    };
    google.a.c.ra = function (a, c) {
        c = c || {};
        for (var d = [], e = 0; e < a.length; e++) {
            var f = a[e];
            if (!c[f]) {
                c[f] = !0;
                var g = google.a.c.Cc[f] || [];
                0 < g.length && (d = d.concat(google.a.c.ra(g, c)));
                d.push(f)
            }
        }
        return d
    };
    google.a.c.Ic = function (a) {
        for (var c = {}, d = [], e = 0; e < a.length; e++) {
            var f = google.a.c.zc[a[e]];
            Z.isArray(f) || (f = [f]);
            for (var g = 0; g < f.length; g++) {
                var h = f[g];
                h && !c[h] && (c[h] = !0, d.push(h))
            }
        }
        return d
    };
    google.a.c.hd = function (a, c) {
        if (c)if ("undefined" === typeof a.onload) {
            var d = !1;
            a.onreadystatechange = function () {
                d || (a.readyState && a.readyState !== L ? google.a.c.window.setTimeout(a.onreadystatechange, 0) : (d = !0, delete a.onreadystatechange, google.a.c.window.setTimeout(c, 0)))
            }
        } else a.onload = c
    };
    google.a.c.zb = function (a, c, d) {
        google.a.c.log("loadScript: " + a);
        var e = c.createElement("script");
        e.type = Xa;
        e.language = "javascript";
        e.async = !1;
        e.defer = !1;
        c = c.body || c.head || c.getElementsByTagName("HEAD").item(0) || c.documentElement;
        c.insertBefore(e, c.lastChild);
        d && google.a.c.hd(e, d);
        e.src = a;
        google.a.c.log("end loadScript: " + a)
    };
    google.a.c.Wc = function (a, c) {
        a = google.a.c.ra(a);
        for (var d = [], e = 0; e < a.length; e++) {
            var f = a[e];
            google.a.c.P[f] || d.push(f)
        }
        a = d;
        if (0 === a.length)c(); else {
            google.a.c.log("Load packages + dependencies - previous: " + a);
            var g = google.a.c.i.prefix, h = google.a.c.i.debug, l = google.a.c.i.compiled, m = google.a.c.i.i18n, n = google.a.c.I, r = google.a.c.Ma;
            "en" === r && (r = null);
            var E = {}, mb = a.length, ob = function () {
                for (var d = [], e = 0; e < a.length; e++)d.push(E[a[e]]);
                eval("0,(function(){" + d.join("") + "})")();
                google.a.c.window.setTimeout(c,
                    0)
            };
            google.a.c.bd = function (a, c) {
                google.a.c.log("callback after loading " + a);
                E[a] = c;
                google.a.c.P[a] = !0;
                mb--;
                0 === mb && ob()
            };
            var nb = function (c) {
                var d = google.a.c.Dc, e = a[c++];
                if (e) {
                    var f = e, E = google.a.c.od[e];
                    E ? (f = E, e === T && (d = window.document), e = google.a.c.i.third_party) : google.a.c.Eb[e] ? (f = google.a.c.Eb[e], e = google.a.c.i.third_party_gen) : e = google.a.c.Y ? h : r ? m : l;
                    f = e.replace(Za, g).replace(ab, n).replace("{language}", r).replace("{package}", f);
                    google.a.c.zb(f, d);
                    nb(c)
                }
            };
            nb(0)
        }
    };
    google.a.c.V = function (a) {
        function c() {
            g = !0;
            for (var a = e.length, c = 0; c < a; c++)e[c]()
        }

        function d() {
            h = !0;
            for (var a = f.length, c = 0; c < a; c++)f[c]()
        }

        var e = [], f = [], g = !1, h = !1;
        google.a.c.V.count || (google.a.c.V.count = 0);
        var l = "load-css-" + google.a.c.V.count++, m = {
            done: function (a) {
                e.push(a);
                g && a();
                return m
            }, Aa: function (a) {
                f.push(a);
                h && a();
                return m
            }
        }, n = document.createElement("link");
        n.setAttribute("id", l);
        n.setAttribute("rel", "stylesheet");
        n.setAttribute("type", "text/css");
        "undefined" !== typeof n.addEventListener ? (n.addEventListener("load",
            c, !1), n.addEventListener("error", d, !1)) : "undefined" !== typeof n.attachEvent && n.attachEvent("onload", function () {
            var a, e = document.styleSheets.length;
            try {
                for (; e--;)if (a = document.styleSheets[e], a.id === l) {
                    c();
                    return
                }
            } catch (f) {
            }
            g || d()
        });
        document.getElementsByTagName("head")[0].appendChild(n);
        n.setAttribute("href", a);
        return m
    };
    google.a.c.Sc = function (a, c) {
        google.a.c.log("loadCSSFile: " + a);
        google.a.c.V(a).done(c).Aa(function () {
            google.a.c.error("loading css failed: " + a)
        })
    };
    google.a.c.Tc = function (a, c) {
        a = google.a.c.ra(a);
        var d = google.a.c.Ic(a);
        if (null === d || 0 === d.length)c(); else {
            google.a.c.log("Loading css files: " + d.join(", "));
            var e = google.a.c.i.prefix, f = google.a.c.i.css;
            google.a.c.Y && (f = google.a.c.i.css_debug || f);
            var g = google.a.c.I, h = function (a) {
                var m = d[a], n;
                n = a < d.length - 1 ? function () {
                    h(a + 1)
                } : c;
                google.a.c.P[m] ? (google.a.c.log("Already loaded " + m), google.a.c.window.setTimeout(n, 0)) : (google.a.c.P[m] = !0, m = f.replace("{css_prefix}", google.a.c.i.css_prefix).replace(Za, e).replace(ab,
                    g).replace("{cssFile}", m), google.a.c.Sc(m, n))
            };
            h(0)
        }
    };
    google.a.c.Cb = function (a) {
        for (var c = a.replace(/-/g, "_").toLowerCase(); Z.h(c);)a = c, c = google.a.c.languages[c], c === a && (c = !1);
        c || (a.match(/_[^_]+$/) ? (a = a.replace(/_[^_]+$/, ""), a = google.a.c.Cb(a)) : a = "en");
        return a
    };
    google.a.c.ed = function (a, c) {
        c.log && (google.a.c.log = c.log);
        c.error && (google.a.c.error = c.error);
        var d = c.debug, e = c.language || "", e = google.a.c.Cb(e);
        a || (a = c.version || "unknown");
        if (google.a.c.I && google.a.c.I !== a || google.a.c.Ma && google.a.c.Ma !== e || google.a.c.Y !== d)google.a.c.P = {};
        google.a.c.I = a;
        google.a.c.Ma = e;
        google.a.c.Y = d
    };
    google.a.c.ea = !0;
    google.a.c.R = !1;
    google.a.c.load = function (a, c, d) {
        if (google.a.c.R)google.a.c.T(function () {
            google.a.c.load(a, c, d)
        }); else {
            var e;
            e = a.match(/^(testing\/)?(.*)/);
            var f = e[1] || "";
            for (a = e[2]; ;) {
                e = google.a.c.ud[a];
                if (null == e || "top" === e)break;
                a = e
            }
            google.a.c.i = d || google.a.c.Ta[a] || google.a.c.Ta.gstatic;
            a = f + a;
            google.a.c.R = !0;
            var g = function () {
                google.a.c.R = !1;
                google.a.c.Na()
            };
            if (null == e)f = function () {
                var e = Z.Ea("google.charts.versionSpecific.load"), f = Z.Ea("google.charts.versionSpecific.setOnLoadCallback");
                e(a, c, d);
                f(g)
            }, google.a.c.ea ?
                (google.a.c.ea = !1, google.a.c.zb(google.a.c.i.prefix + "/" + a + "/loader.js", window.document, f)) : f(); else {
                if (!google.a.c.ea)throw Error("google.charts.load() cannot be called more than once with version 44 or earlier.");
                google.a.c.ea = !1;
                google.a.c.ed(a, c);
                google.a.c.log("google.charts.load version " + a);
                window.google = window.google || {};
                google.visualization = google.visualization || {};
                google.visualization.ModulePath = google.a.c.i.prefix;
                google.visualization.CssPath = google.a.c.i.css_prefix.replace(Za, google.a.c.i.prefix).replace(ab,
                    google.a.c.I);
                google.a.c.window = window;
                google.a.c.Dc = document;
                var h = c.packages;
                h && 0 !== h.length || (h = ["default"]);
                google.a.c.T(c.callback);
                google.a.c.Tc(h, function () {
                    google.a.c.Wc(h, g)
                })
            }
        }
    };
    google.a.c.jd = function (a) {
        if (window.addEventListener)window.addEventListener("load", a, !1); else if (window.attachEvent)window.attachEvent("onload", a); else {
            var c = window.onload;
            window.onload = function (d) {
                c && c(d);
                a()
            }
        }
    };
    google.a.c.Jb = document && document.readyState === L;
    google.a.c.jd(function () {
        google.a.c.Jb = !0;
        google.a.c.Na()
    });
    google.a.c.Na = function () {
        !google.a.c.R && google.a.c.Jb && google.a.c.wc()
    };
    google.a.c.Ba = [];
    google.a.c.T = function (a) {
        a && google.a.c.Ba.push(a);
        google.a.c.R || google.a.c.Na()
    };
    google.a.c.wc = function () {
        var a = google.a.c.Ba;
        for (google.a.c.Ba = []; 0 < a.length;)a.shift()()
    };
    google.a.c.Qa = function (a, c) {
        google.a.c.bd(a, c)
    };
    if (Z.Ea(Wa))throw Error("Google Charts loader.js can only be loaded once.");
    google.a.load = function () {
        var a = 0;
        "visualization" === arguments[a] && a++;
        var c = "current";
        Z.h(arguments[a]) && (c = arguments[a], a++);
        var d = {};
        arguments.length > a && (d = arguments[a], a++);
        var e = void 0;
        arguments.length > a && (e = arguments[a]);
        google.a.c.load(c, d, e)
    };
    google.a.T = function (a) {
        google.a.c.T(a)
    };
    google.a.Qa = function (a, c) {
        google.a.c.Qa(a, c)
    };
    Z.ya(Wa, google.a.load);
    Z.ya("google.charts.setOnLoadCallback", google.a.T);
    Z.ya("google.charts.packageLoadedCallback", google.a.Qa);
})();
