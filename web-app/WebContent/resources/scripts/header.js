document.addEventListener("DOMContentLoaded", () => {
  const dropdown = document.querySelector(".dropdown");
  const dropdownContent = document.querySelector(".dropdown-content");

  if (dropdown && dropdownContent) {
    dropdown.addEventListener("click", (e) => {
      e.stopPropagation();
      dropdownContent.style.display =
        dropdownContent.style.display === "block" ? "none" : "block";
    });

    document.addEventListener("click", () => {
      dropdownContent.style.display = "none";
    });
  }

  const searchInput = document.querySelector('input[name="search"]');
  const resultsContainer = document.getElementById('search-results');
  const appRoot = document.getElementById('app-root');
  const contextPath = appRoot ? appRoot.dataset.contextPath : '';

  if (searchInput && resultsContainer) {
    searchInput.addEventListener('input', () => {
      const query = searchInput.value.trim();

      if (query.length < 3) {
        resultsContainer.innerHTML = '';
        resultsContainer.style.display = 'none';
        return;
      }

      fetch(`${contextPath}/SearchbarServlet?search=${encodeURIComponent(query)}`)
        .then(response => {
          if (!response.ok) {
            throw new Error(`Errore ${response.status}`);
          }
          return response.json();
        })
        .then(data => {
          if (!Array.isArray(data) || data.length === 0) {
            resultsContainer.innerHTML = '<div class="product-result">Nessun risultato trovato</div>';
          } else {
            resultsContainer.innerHTML = data.map(prod => `
              <div class="product-result" onclick="window.location.href='${contextPath}/ProductPage?prodId=${prod.id}'">
                <strong>${prod.name}</strong><br>
                <small>${prod.size}</small><br>
                <small>${prod.description}</small><br>
                Prezzo: €${prod.price}
              </div>
            `).join('');
          }
          resultsContainer.style.display = 'block';
        })
        .catch(error => {
          console.error("Errore durante la richiesta AJAX:", error);
          resultsContainer.innerHTML = '<div class="product-result">Errore nella ricerca</div>';
          resultsContainer.style.display = 'block';
        });
    });

    document.addEventListener('click', (event) => {
      if (!event.target.closest('.searchbar')) {
        resultsContainer.style.display = 'none';
      }
    });
  }

  const searchIcon = document.querySelector('.icons .search-icon');
  const searchbar = document.querySelector('.searchbar');

  if (searchIcon && searchbar) {
    searchIcon.addEventListener('click', function () {
      searchbar.classList.toggle('active');
      if (searchbar.classList.contains('active')) {
        searchbar.querySelector('input').focus();
      }
    });
  }
});
