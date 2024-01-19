console.log('boardComment.js에 들어왔다~~');
//console.log(bnoVal);

document.getElementById('cmtPostBtn').addEventListener('click',()=>{
    const cmtText = document.getElementById('cmtText');
    if(cmtText == 'null' || cmtText == ''){
        alert('댓글을 입력해주세요')
        cmtText.focus();
        return false;
    }else{
        let cmtData = {
            bno : bnoVal,
            writer : document.getElementById('cmtWriter').innerHTML,
            content : cmtText.value
        };
        console.log(cmtData);

        // 댓글등록
        postCommentToserver(cmtData).then(result =>{
            if(result == '1'){
                console.log('댓글 등록 성공');
                cmtText.value = "";
            }
            // 화면에 뿌리기
            spreadCommentList(cmtData.bno);
        })
    }
});


async function postCommentToserver(cmtData){
    try{

        const url = "/comment/post";
        const config = {
            method : "post",
            headers : {
                'content-type' : 'application/json; charset=utf-8' 
            },
            body : JSON.stringify(cmtData)
        };

        const resp = await fetch(url , config);
        const result = await resp.text();
        return result;

    }catch(error){
        console.log(error);
    }
};


async function getCommentListFromServer(bno){
    try{

        const resp = await fetch("/comment/" + bno);
        const result = await resp.json();
        return result;

    }catch(error){
        console.log(error);
    }
};


function spreadCommentList(bno) {
    getCommentListFromServer(bno).then(result=>{
        console.log(result);
        const ul = document.getElementById('cmtListArea');
        ul.innerHTML = '';
        if(result != null) {
            for(let cvo of result) {
                let li = `<li class="list-group-item" data-cno="${cvo.cno}" data-writer="${cvo.writer}">`;
                li += `<div class="mb-3">`;
                li += `<div class="fw-bold">${cvo.writer} `;
                li += `<span class="badge rounded-pill text-bg-warning">${cvo.modAt}</span></div>`;
                li += `${cvo.content}`;
                li += `</div>`;
                li += `<button type="button" class="btn btn-sm btn-outline-success cmtModBtn" data-bs-toggle="modal" data-bs-target="#myModal">Eidt</button>`;
                li += `<button type="button" class="btn btn-sm btn-outline-danger cmtDelBtn">Delete</button>`;
                li += `</li>`;
                ul.innerHTML += li;
            }            
        } else {
            let li = `<li class="list-group-item">댓글이 없습니다.</li>`;
            ul.innerHTML = li;
        }
    })
};