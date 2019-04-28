getYMDHMSFormat = function(date, gubun) {
    if (!date) {
        return "";
    }
    date = removeNoneNumDate(date);

    if (date.length != 14) return date;

    if (typeof(gubun) == "undefined") {
        gubun = "-";
    }
    return getDateString("YYYY" + gubun + "MM" + gubun + "DD hh:mm:ss", date);
};
removeNoneNumDate = function(val) {
    var reg=/[^\d]/;

    while (reg.test(val)) {
        val = val.replace(reg, "");
    }
    return val;
};
Date.MONTHS = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
Date.DAYS   = ["Sun", "Mon", "Tue", "Wed", "Tur", "Fri", "Sat"];

getDateString = function(dateFormat, date) {
    var now = new Date();
    var result = "";
    var SdateY = 0;
    var SdateM = 0;
    var SdateD = 0;
    var SdateH = 0;
    var Sdatem = 0;
    var SdateS = 0;

    if (typeof(date) != "undefined") {
        if (date.length == 4) {	// hhmm
            SdateH = date.substring(0,2);
            Sdatem = date.substring(2,4);
            now.setHours(SdateH, Sdatem);
        } else if (date.length == 6) {	// hhmmss
            SdateH = date.substring(0,2);
            Sdatem = date.substring(2,4);
            SdateS = date.substring(4,6);
            now.setHours(SdateH, Sdatem, SdateS);
        } else if (date.length >= 8) {	// yyyymmdd
            SdateY = date.substring(0,4);
            SdateM = date.substring(4,6);
            SdateD = date.substring(6,8);

            if (date.length >= 12) {	// yyyymmddhhmmss
                SdateH = date.substring(8,10);
                Sdatem = date.substring(10,12);
            }

            if (date.length >= 14) {
                SdateS = date.substring(12,14);
            }
            now = new Date(SdateY, Number(SdateM) - 1, SdateD, SdateH, Sdatem, SdateS);
        } else {
            return date;
        }
    }
    dateFormat = dateFormat == 8 && "YYYY.MM.DD" ||
                 dateFormat == 6 && "hh:mm:ss" ||
                 dateFormat || "YYYY.MM.DD hh:mm:ss";

    for (var i = 0; i < dateFormat.length; i++) {
        result += dateFormat.indexOf("YYYY", i) == i ? (i += 3, now.getFullYear()                     ) :
                  dateFormat.indexOf("YY",   i) == i ? (i += 1, String(now.getFullYear()).substring(2)) :
                  dateFormat.indexOf("MMM",  i) == i ? (i += 2, Date.MONTHS[now.getMonth()]           ) :
                  dateFormat.indexOf("MM",   i) == i ? (i += 1, (now.getMonth()+1).to2()              ) :
                  dateFormat.indexOf("M",    i) == i ? (        now.getMonth()+1                      ) :
                  dateFormat.indexOf("DDD",  i) == i ? (i += 2, Date.DAYS[now.getDay()]               ) :
                  dateFormat.indexOf("DD",   i) == i ? (i += 1, now.getDate().to2()                   ) :
                  dateFormat.indexOf("D"   , i) == i ? (        now.getDate()                         ) :
                  dateFormat.indexOf("hh",   i) == i ? (i += 1, now.getHours().to2()                  ) :
                  dateFormat.indexOf("h",    i) == i ? (        now.getHours()                        ) :
                  dateFormat.indexOf("mm",   i) == i ? (i += 1, now.getMinutes().to2()                ) :
                  dateFormat.indexOf("m",    i) == i ? (        now.getMinutes()                      ) :
                  dateFormat.indexOf("ss",   i) == i ? (i += 1, now.getSeconds().to2()                ) :
                  dateFormat.indexOf("s",    i) == i ? (        now.getSeconds()                      ) :
                                                       (dateFormat.charAt(i)                          ) ;
    }
    return result;
};

Number.prototype.to2 = function() {
    return (this > 9 ? "" : "0") + this;
};