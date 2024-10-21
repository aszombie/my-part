<template>
    <footer class="footer-section">
        <div class="">
            <div class="footer-text">
                <div class="row">
                    <!-- About Section -->
                    <div class="col-lg-4">
                        <div class="ft-about">
                            <div class="logo">
                                <a href="#">
                                    <img src="/img/logo3.png" alt="Footer Logo" />
                                </a>
                            </div>
                            <p>
                                We inspire and reach millions of travelers<br />
                                across 90 local websites
                            </p>
                            
                        </div>
                    </div>
                    <!-- Contact Section -->
                    <div class="col-lg-3 ">
                        <div class="ft-contact">
                            <h6>Contact Us</h6>
                            <ul>
                                <li>(12) 345 67890</li>
                                <li>info.colorlib@gmail.com</li>
                                <li>856 Cordia Extension Apt. 356, Lake, United States</li>
                            </ul>
                        </div>
                    </div>
                    <!-- Newsletter Section -->
                    <div class="col-lg-3 offset-lg-1">
                        <div class="ft-newslatter">
                            <h6>New latest</h6>
                            <p>Get the latest updates and offers.</p>
                            <form action="#" class="fn-form">
                                <input type="text" placeholder="Email" />
                                <button type="submit"><i class="fas fa-paper-plane"></i></button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </footer>
</template>

<script setup>

</script>


<style scoped>
footer {
    margin: 0;
    padding: 0;
    width: 100%;
    margin-top: auto;
    /* 确保 footer 在页面底部 */
}

.footer-section {
    background: #222736;
    padding: 20px 0;
    width: 100%;
    box-sizing: border-box;
    position: relative;
    /* 将 position 从 relative 改为 static 或保持默认，
       避免影响 footer 定位 */
}

.footer-section .container {
    max-width: 1300px;
    margin: 0 auto;
    padding: 0;
    box-sizing: border-box;
}


.footer-section .footer-text {
    padding: 80px 0 30px;
}

.footer-section .footer-text .ft-about {
    margin-bottom: 30px;
}

.footer-section .footer-text .ft-about .logo {
    margin-bottom: 20px;
}

.footer-section .footer-text .ft-about .logo a {
    display: inline-block;
}

.footer-section .footer-text .ft-about .logo img {
    max-width: 150px;
    /* Adjust this value as needed */
    height: auto;
    /* Maintain aspect ratio */
}

.footer-section .footer-text .ft-about p {
    color: #aaaab3;
    margin-bottom: 20px;
}

.footer-section .footer-text .ft-about .fa-social a {
    display: inline-block;
    height: 40px;
    width: 40px;
    font-size: 16px;
    line-height: 40px;
    text-align: center;
    color: #ffffff;
    border: 1px solid #5A4D48;
    border-radius: 50%;
    transition: all 0.3s;
    margin-right: 7px;
}

.footer-section .footer-text .ft-about .fa-social a:hover {
    background: #dfa974;
    border-color: #dfa974;
}

.footer-section .footer-text .ft-contact {
    margin-bottom: 30px;
}

.footer-section .footer-text .ft-contact h6 {
    font-size: 14px;
    color: #dfa974;
    text-transform: uppercase;
    font-weight: 700;
    letter-spacing: 3px;
    font-family: "Cabin", sans-serif;
    margin-bottom: 20px;
}

.footer-section .footer-text .ft-contact ul li {
    font-size: 16px;
    color: #aaaab3;
    line-height: 32px;
    list-style: none;
}

.footer-section .footer-text .ft-newslatter h6 {
    font-size: 14px;
    color: #dfa974;
    text-transform: uppercase;
    font-weight: 700;
    letter-spacing: 3px;
    font-family: "Cabin", sans-serif;
    margin-bottom: 20px;
}

.footer-section .footer-text .ft-newslatter p {
    color: #aaaab3;
    margin-bottom: 20px;
}

.footer-section .footer-text .ft-newslatter .fn-form {
    position: relative;
}

.footer-section .footer-text .ft-newslatter .fn-form input {
    width: 100%;
    height: 50px;
    border-radius: 2px;
    background: #393D4A;
    border: none;
    padding-left: 20px;
    font-size: 16px;
    color: #707079;
}

.footer-section .footer-text .ft-newslatter .fn-form button {
    position: absolute;
    right: 0;
    top: 0;
    font-size: 16px;
    background: #dfa974;
    color: #ffffff;
    padding: 0 16px;
    height: 50px;
    border: none;
    border-radius: 0 2px 2px 0;
}

.footer-section .copyright-option ul li {
    margin-right: 25px;
}

</style>