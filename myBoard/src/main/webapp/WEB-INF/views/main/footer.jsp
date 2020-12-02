 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 
  <style>
  .site-footer
{
  background-color: #F5F5F5;
  padding:45px 0 20px;
  font-size:15px;
  line-height:24px;
  color:#737373;
}
.site-footer hr
{
  border-top-color:#bbb;
  opacity:0.5
}

.site-footer hr.small
{
  margin:20px 0
}
.site-footer h6
{
  color:#fff;
  font-size:16px;
  text-transform:uppercase;
  margin-top:5px;
  letter-spacing:2px
}
.site-footer a
{
  color:#737373;
}
.site-footer a:hover
{
  color:#3366cc;
  text-decoration:none;
}
.footer-links
{
  padding-left:0;
  list-style:none
}
.footer-links li
{
  display:block
}
.footer-links a
{
  color:#737373
}
.footer-links a:active,.footer-links a:focus,.footer-links a:hover
{
  color:#3366cc;
  text-decoration:none;
}
.footer-links.inline li
{
  display:inline-block
}
.copyright-text
{
  margin:0
}
@media (max-width:991px)
{
  .site-footer [class^=col-]
  {
    margin-bottom:30px
  }
}
@media (max-width:767px)
{
  .site-footer
  {
    padding-bottom:0
  }
  .site-footer .copyright-text,.site-footer .social-icons
  {
    text-align:center
  }
}
@media (max-width:767px)
{
  .social-icons li.title
  {
    display:block;
    margin-right:0;
    font-weight:600
  }
}
  </style>
  <div style="margin-top: 50px;">
  </div>
  <!-- Site footer -->
    <footer class="site-footer">
      <div class="container">
        <div class="row">
          <div class="col-sm-12 col-md-6">
            <h3>About</h3>
            <p class="text-justify">Spring, jsp, html, css, javascript, S3를 이용해서 만든 게시판 앱입니다. 회원 기능, 게시판 기능, 파일 업로드 및 다운로드 기능, 통계 기능이 있습니다.</p>
          </div>


          <div class="col-xs-6 col-md-3">
            <h3>Quick Links</h3>
            <ul class="footer-links">
              <li style="font-size:1.2em"><a href="https://github.com/osk14741/board">
              	<img style="display:inline;" width="25px" height="25px" src="${hContext }/resources/img/github-logo.svg"></img>  GIT-HUB</a>
              </li>
            </ul>
          </div>
          
         
        </div>
        <hr>
      </div>
</footer>