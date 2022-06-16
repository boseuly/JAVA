window.onload=function (){
    // DOM으로 작성한 경우 
    // var p_tags = document.getElementsByClassName('copy1');
    // for(p of p_tags){
    //     p.addEventListener("mouseover", function() {
    //         this.style.color = 'red';
    //     })
    //     p.addEventListener("mouseout", function (){
    //         this.style.color = 'black';
    //     })
    // }
    
    // .clone(true)에 대한 이벤트 핸들러 복제는 JQuery 로 연결된 이벤트 핸들러에
    //대해서만 적용된다. 기존 DOM의 addEventListener로 등록된 이벤트 핸들러는 동작하지 않는다!!!
    // 위에 dom을  jquery 로 만들면 ?!
        // $('.copy1').bind("mouseover", function (){      // 마우스를 올려두면 red 로 바꿔라
        //     this.style.color='red';
        // })
        // $('.copy1').bind("mouseout", function (){       // 마우스를 
        //     this.style.color='black';
        // })
        
    // 위에 거를 좀더 편하게 하는 법
    $(".copy1").hover(function () {
        this.style.color = 'red';
    })


        var inputs = document.getElementsByTagName("input");
        for(input of inputs){
            input.addEventListener("input", function (){
                console.log(this.value);
        });
    }
}
