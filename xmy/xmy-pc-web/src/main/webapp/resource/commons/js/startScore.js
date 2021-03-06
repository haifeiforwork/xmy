function scoreFun(object, opts) {
    var defaults = {
        fen_d: 15,
        ScoreGrade: 10,
        types: ["很不满意", "差得太离谱", "不满意", "部分有破损", "一般", "质量一般", "没有卖好", "满意", "质量不错一致", "非常满意", "质量非常好，非常满意啊"],
        nameScore: "fenshu",
        parent: "star_score",
        attitude: "attitude"
    };
    options = $.extend({},
    defaults, opts);
    var countScore = object.find("." + options.nameScore);
    var startParent = object.find("." + options.parent);
    var atti = object.find("." + options.attitude);
    var score_txt = object.find(".fenshu_score");
    var now_cli;
    var fen_cli;
    var atu;
    var fen_d = options.fen_d;
    var len = options.ScoreGrade;
    startParent.width(fen_d * len);
    var preA = (5 / len);
    for (var i = 0; i < len; i++) {
        var newSpan = $("<a href='javascript:void(0)'></a>");
        newSpan.css({
            "left": 0,
            "width": fen_d * (i + 1),
            "z-index": len - i
        });
        newSpan.appendTo(startParent)
    }
    startParent.find("a").each(function(index, element) {
        $(this).click(function() {
            now_cli = index;
            show(index, $(this))
        });
        $(this).mouseenter(function() {
            show(index, $(this))
        });
        $(this).mouseleave(function() {
            if (now_cli >= 0) {
                var scor = preA * (parseInt(now_cli) + 1);
                startParent.find("a").removeClass("clibg");
                startParent.find("a").eq(now_cli).addClass("clibg");
                var ww = fen_d * (parseInt(now_cli) + 1);
                startParent.find("a").eq(now_cli).css({
                    "width": ww,
                    "left": "0"
                });
				
                if (countScore) {
                    atti.text(options.types[parseInt(scor)]);
                    score_txt.text(scor*2);
					countScore.val(scor);
                }
				
            } else {
                startParent.find("a").removeClass("clibg");
                
				if (countScore) {
                    atti.text("");
                    score_txt.text(0);
					countScore.val(0);
                }
				
            }
        })
    });
    function show(num, obj) {
        var n = parseInt(num) + 1;
        var lefta = num * fen_d;
        var ww = fen_d * n;
        var scor = preA * n;
        atu = options.types[parseInt(num)];
        object.find("a").removeClass("clibg");
        obj.addClass("clibg");
        obj.css({
            "width": ww,
            "left": "0"
        });
        //countScore.text(scor);
		countScore.val(scor);
        //atti.text(atu)
    }
};