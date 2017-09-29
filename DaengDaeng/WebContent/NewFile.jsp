<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>자유게시판 글쓰기</title>
<link href="/DaengDaeng/css/main.css" rel="stylesheet" type="text/css">
<link href="/DaengDaeng/css/myPageForm.css" rel="stylesheet"
   type="text/css">
<link href="/DaengDaeng/css/board_qna_list_write.css" rel="stylesheet"
   type="text/css">
<script src="https://code.jquery.com/jquery-1.9.0.min.js"
   integrity="sha256-f6DVw/U4x2+HjgEqw5BZf67Kq/5vudRZuRkljnbF344="
   crossorigin="anonymous"></script>
<link
   href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css"
   rel="stylesheet">
<script
   src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script
   src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<link
   href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.7/summernote.css"
   rel="stylesheet">
<script
   src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.7/summernote.js"></script>

<script>
   $(document).ready(function() {
      $('#summernote').summernote({ // summernote를 사용하기 위한 선언
         width : 800,
         height : 400,
         callbacks : {
            onImageUpload : function(files, editor, welEditable) {
               console.log(files);
               console.log(files[0]);
               var opt = {};
               //for (var i = files.length - 1; i >= 0; i--) {
               //   files[i]; //해당파일들을 ajax로 한번씩 FormData로담아서 보내거나 다양하게 처리하시믄됩니다.   
               sendFile(files[0], editor, welEditable);

               //var url = "http://localhost:8081/test/upload/"
               //$('#summernote').summernote('insertImage',url+ files[i].name,files[i].name);

               //}

            }
         }
      });

   });
   function summer() {
      var markStr = $('#summernote').summernote('code');
      $("#content").val(markStr);
   }

   function sendFile(file, editor, welEditable) {
      data = new FormData();
      data.append("uploadFile", file);
      //var loading = $('<img alt="loading" src="/DaengDaeng/images/ajax-loader.gif" />').appendTo(document.body).hide();

      $.ajax({
         data : data,
         type : "POST",
         url : "/DaengDaeng/fileupload.jsp",
         cache : false,
         contentType : false,
         processData : false,

         success : function(data) {
            setTimeout(function() {
               alert(data.name);
               var url = "http://localhost:8081/DaengDaeng/upload/";
               $('#summernote').summernote(
                     'insertImage',
                     'http://localhost:8081/DaengDaeng/upload/'
                           + data.name, data.name);
            }, 3000);
            //   alert(data.name);
            //   var url = "http://localhost:8081/DaengDaeng/upload/";
            //   $('#summernote').summernote('insertImage','http://localhost:8081/DaengDaeng/upload/'+data.name,data.name);

         }
      });
   }
   

   
</script>
</head>
<body>
   <div id="wrapper">
     
            
      <div id="contents_qna">
         <div class="board_qna">
            <div class="write_title_qna">
               <h2>글쓰기</h2>
            </div>
            <div class="write_area_qna">
               <form method="post" name="writeform"
                  action="/DaengDaeng/board/free/free_writePro.do" onsubmit="summer();">
                  <input type="hidden" name="board_num" value="${board_num}">
                  <input type="hidden" name="board_ref" value="${board_ref}">
                  <input type="hidden" name="board_step" value="${board_step}">
                  <input type="hidden" name="board_level" value="${board_level}">
                  <input type="hidden" name="board_type" value="자유">

                  <ul>
                     <li class="write_writer_qna"><label>작성자</label>
                        <h3>${memberDTO.mem_name}</h3></li>
                     <li class="write_tit_qna"><label>제 목</label> <c:if
                           test="${num == 0}">
                           <h3>
                              <input type="text" size="40" maxlength="50" name="board_title">
                           </h3>
                        </c:if> <c:if test="${num != 0}">
                           <h3>
                              <input type="text" size="40" maxlength="50" name="board_title"
                                 value="[답변]">
                           </h3>
                        </c:if></li>
                  </ul>
                  <div class="write_box_qna">
                     <div class="write_box_qna">
                        <div id="summernote">Hello Summernote</div>
                        <input name="board_contents" id="content" type="hidden">
                     </div>
                     <div class="btn_write_qna">
                        <input type="submit" value="글쓰기"> <input type="reset"
                           value="다시작성"> <input type="button" value="목록보기"
                           OnClick="window.location='/DaengDaeng/board/free/free_content.do'">
                     </div>
                  </div>
               </form>
            </div>
         </div>
      
      </div>
         
   </div>
</body>
</html>