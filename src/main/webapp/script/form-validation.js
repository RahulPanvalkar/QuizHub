// Example starter JavaScript for disabling form submissions if there are invalid fields
(function () {
  'use strict'

  // Fetch all the forms we want to apply custom Bootstrap validation styles to
  var forms = document.querySelectorAll('.needs-validation')

  // Loop over them and prevent submission
  Array.prototype.slice.call(forms)
    .forEach(function (form) {
      form.addEventListener('submit', function (event) {
        if (!form.checkValidity()) {
          event.preventDefault()
          event.stopPropagation()
        }

        form.classList.add('was-validated')
      }, false)
    })
})();


// Select all input fields inside the form
const formInputs = document.querySelectorAll("input[type='text'], input[type='email'], input[type='password']");

// Add an event listener to each input field
formInputs.forEach(input => {
    input.addEventListener("focus", function() {
        this.value = "";
    });
});
