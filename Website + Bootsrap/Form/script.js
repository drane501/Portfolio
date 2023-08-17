function validate() {
    
    var fname = document.getElementById('fname').value;
    var lname = document.getElementById('lname').value;
    var address = document.getElementById('aname').value;
    var city = document.getElementById('cname').value;
    var zip = document.getElementById('zname').value;
    var phone = document.getElementById('pname').value;
    var email = document.getElementById('ename').value;
    
    //Used to confirm email and number format, I took these values off google
                var num = /^[0-9]+$/;
                var emailConfig = /^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$/;

                if (fname.value == "" || fname.value.length <= 1) {
                    alert("Please enter your first name.");
                }

                if (lname.value == "" || lname.value.length <= 1) {
                    alert("Please enter your last name.");
                }

                if (address.value == "" || address.value.length <= 1) {
                    alert("Please enter a valid address.");
                }

                if (city.value == "" || city.value.length <= 1) {
                    alert("Please enter a valid city.");
                }

                if (!zip.match(num)) || zip.length != 5) {
                    alert("Please enter a valid zip code");
                }

                if (!phone.match(num)) || phone.length != 10) {
                    alert("Please enter a valid telephone number.");
                }

                if (!email.match(emailConfig) {
                    alert("Please enter a valid e-mail address.");
                }
}