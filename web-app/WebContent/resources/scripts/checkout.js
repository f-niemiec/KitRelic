function toggleShippingForm() {
  const checkbox = document.getElementById('sameAsBilling');
  const shippingForm = document.getElementById('shippingForm');
  if (checkbox && shippingForm) {
    shippingForm.style.display = checkbox.checked ? 'none' : 'block';
  }
}

function validateCheckout(event) {
  const form = event.target;
  const billingInput = document.getElementById('billingSaved');
  const shippingInput = document.getElementById('shippingSaved');
  const sameAsBilling = document.getElementById('sameAsBilling');
  const errorDiv = document.getElementById('address-missing');
  const billingSaved = billingInput && billingInput.value === 'true';
  const shippingSaved = shippingInput && shippingInput.value === 'true';
  const usingSame = sameAsBilling && sameAsBilling.checked;
  if (form.action.includes('AddPaymentCardServlet')) {
    if (!billingSaved || (!usingSame && !shippingSaved)) {
      if (errorDiv) errorDiv.style.display = 'block';
      event.preventDefault();
      return false;
    } else {
      if (errorDiv) errorDiv.style.display = 'none';
    }
  }
  const cityRegex = /^[A-Za-zÀ-ÿ\s\-']+$/;
  const streetRegex = /^[A-Za-zÀ-ÿ0-9\s,\-']+$/;
  const provinceRegex = cityRegex;
  const countryRegex = cityRegex;
  const cardNumberRegex = /^\d{16}$/;
  const ownerRegex = /^[A-Za-zÀ-ÿ\s\-']+$/;
  const cvvRegex = /^\d{3,4}$/;
  const city = form.querySelector('input[name="city"]');
  const street = form.querySelector('input[name="street"]');
  const province = form.querySelector('input[name="province"]');
  const country = form.querySelector('input[name="country"]');
  const cardNumber = form.querySelector('#cardNumber');
  const owner = form.querySelector('#owner');
  const expires = form.querySelector('#expires');
  const cvv = form.querySelector('#cvv');
  let valid = true;

  if (city && !cityRegex.test(city.value.trim())) {
    city.classList.add('input-error');
    const err = form.querySelector('#error-city');
    if (err) err.style.display = 'block';
    valid = false;
  } else if (city) {
    city.classList.remove('input-error');
    const err = form.querySelector('#error-city');
    if (err) err.style.display = 'none';
  }
  if (street && !streetRegex.test(street.value.trim())) {
    const err = form.querySelector('#error-street');
    if (err) err.style.display = 'block';
    valid = false;
  } else if (street) {
    const err = form.querySelector('#error-street');
    if (err) err.style.display = 'none';
  }
  if (province && !provinceRegex.test(province.value.trim())) {
    province.classList.add('input-error');
    const err = form.querySelector('#error-province');
    if (err) err.style.display = 'block';
    valid = false;
  } else if (province) {
    province.classList.remove('input-error');
    const err = form.querySelector('#error-province');
    if (err) err.style.display = 'none';
  }
  if (country && !countryRegex.test(country.value.trim())) {
    country.classList.add('input-error');
    const err = form.querySelector('#error-country');
    if (err) err.style.display = 'block';
    valid = false;
  } else if (country) {
    country.classList.remove('input-error');
    const err = form.querySelector('#error-country');
    if (err) err.style.display = 'none';
  }
  if (cardNumber && !cardNumberRegex.test(cardNumber.value.trim())) {
    const err = form.querySelector('#error-card');
    if (err) err.style.display = 'block';
    valid = false;
  } else if (cardNumber) {
    const err = form.querySelector('#error-card');
    if (err) err.style.display = 'none';
  }
  if (owner && !ownerRegex.test(owner.value.trim())) {
    const err = form.querySelector('#error-owner');
    if (err) err.style.display = 'block';
    valid = false;
  } else if (owner) {
    const err = form.querySelector('#error-owner');
    if (err) err.style.display = 'none';
  }
  if (expires && expires.value.trim() === '') {
    const err = form.querySelector('#error-expires');
    if (err) err.style.display = 'block';
    valid = false;
  } else if (expires) {
    const err = form.querySelector('#error-expires');
    if (err) err.style.display = 'none';
  }
  if (cvv && !cvvRegex.test(cvv.value.trim())) {
    const err = form.querySelector('#error-cvv');
    if (err) err.style.display = 'block';
    valid = false;
  } else if (cvv) {
    const err = form.querySelector('#error-cvv');
    if (err) err.style.display = 'none';
  }
  if (!valid) event.preventDefault();
  return valid;
}

document.addEventListener('DOMContentLoaded', function () {
    const checkbox = document.getElementById('sameAsBilling');
    const shippingSavedField = document.getElementById('shippingSaved');

    if (checkbox && shippingSavedField) {
      checkbox.addEventListener('change', () => {
        shippingSavedField.value = checkbox.checked ? 'true' : 'false';
      });
    }
  });
