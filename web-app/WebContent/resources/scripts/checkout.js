function toggleShippingForm() {
    const checkbox = document.getElementById('sameAsBilling');
    const shippingForm = document.getElementById('shippingForm');
    if (checkbox.checked) {
      shippingForm.style.display = 'none';
    } else {
      shippingForm.style.display = 'block';
    }
  }