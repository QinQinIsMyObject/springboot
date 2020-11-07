/**
 * 从url中获取参数的函数
 * @param {*} variable 
 */
function getQV(variable) {
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i=0;i<vars.length;i++) {
        var pair = vars[i].split("=");
        if(pair[0] == variable){return pair[1];}
    }
    return(false);
}

function getX(obj){
    var parObj=obj;
    var left=obj.offsetLeft;
    while(parObj=parObj.offsetParent){
        left+=parObj.offsetLeft ;
    }
    return window.event.clientX-left+document.documentElement.scrollLeft ;
}
    
function getY(obj){
    var parObj=obj;
    var top=obj.offsetTop;
    while(parObj = parObj.offsetParent){
        top+=parObj.offsetTop;
    }
    return window.event.clientY-top+document.documentElement.scrollTop ;
}

function checkUsername(username) {
    let regex1 = /^[a-zA-Z]+[a-zA-Z0-9_]+/
    let regex2 = /[a-zA-Z0-9_]{4,20}@(qq|126|163|QQ|gmail)(\.com|\.cn|\.com\.cn)$/
    return (regex1.test(username) && username.length >=5 && username.length <= 16) || regex2.test(username)
}


//15秒后被记录
setTimeout(() => {
    http.post('/api/record/user/visit').then(resp => { console.log(resp) })
}, 1000 * 15)

