function addToCartItem(div){

  var qty = document.getElementById('inputQuantity').value;
  var id = div.getAttribute("data-id");

   const form = document.createElement('form');
   form.method = 'POST';
   //set form th:action to add-product in basket controller
   form.action = '/basket/add';

   var hiddenField = document.createElement('input');
   hiddenField.type = 'hidden';
   hiddenField.name = 'productId';
   hiddenField.value = id;

   form.appendChild(hiddenField);
   hiddenField = document.createElement('input');
   hiddenField.type='hidden';
   hiddenField.name= 'quantity';
   hiddenField.value= qty;
   form.appendChild(hiddenField);
   document.body.appendChild(form);
   form.submit();
 }



function addToCart(div){
    var id = div.getAttribute("data-id");
    // console.log(id);
 //   alert(id);
 
   const form = document.createElement('form');
   form.method = 'POST';
   //set form th:action to add-product in basket controller
   form.action = '/basket/add';

   var hiddenField = document.createElement('input');
   hiddenField.type = 'hidden';
   hiddenField.name = 'productId';
   hiddenField.value = id;
   form.appendChild(hiddenField);
   hiddenField = document.createElement('input');
   hiddenField.type='hidden';
   hiddenField.name= 'quantity';
   hiddenField.value=1;
   form.appendChild(hiddenField);
   document.body.appendChild(form);
   form.submit();

 }

 function selectItem(a){

    var id = a.getAttribute("data-id");
     console.log(id);
 //   alert(id);
 const form = document.createElement('form');
   form.method = 'POST';
   //set form th:action to add-product in basket controller
   form.action = '/select-item';

   var hiddenField = document.createElement('input');
   hiddenField.type = 'hidden';
   hiddenField.name = 'productId';
   hiddenField.value = id;
   form.appendChild(hiddenField);
    document.body.appendChild(form);
   form.submit();
 
 }


 function category_button(cat_name){

  //console.log(cat_name);

   const form = document.createElement('form');
   form.method = 'POST';
   //set form th:action to add-product in basket controller
   form.action = '/category';

   var hiddenField = document.createElement('input');
   hiddenField.type = 'hidden';
   hiddenField.name = 'category';
   hiddenField.value = cat_name;
   form.appendChild(hiddenField);
  document.body.appendChild(form);
  form.submit();

 }

 function price_button(lowToHigh) {
    const form = document.createElement('form');
    form.method = 'POST';
     //set form th:action to add-product in basket controller
     form.action = '/pricelowtohigh';
     //var hiddenField = document.createElement('input');
    // hiddenField.type = 'hidden';
    // hidddenField.name = 'price';
    // hiddenField.value = lowToHigh;
    document.body.appendChild(form);
    form.submit();
   }

   function price_button2(Hightolow) {
   const form = document.createElement('form');
     form.method = 'POST';
     //set form th:action to add-product in basket controller
     form.action = '/pricehightolow';
     //var hiddenField = document.createElement('input');
    // hiddenField.type = 'hidden';
    // hidddenField.name = 'price';
    // hiddenField.value = Hightolow;
     document.body.appendChild(form);
    form.submit();
   }

   function name_order(AtoZ){
    const form = document.createElement('form');
     form.method = 'POST';
     //set form th:action to add-product in basket controller
     form.action = '/nameAtoZ';
     //var hiddenField = document.createElement('input');
    // hiddenField.type = 'hidden';
    // hidddenField.name = 'name';
    // hiddenField.value = AtoZ;
     document.body.appendChild(form);
    form.submit();
   }

   function name_order2(ZtoA){
    const form = document.createElement('form');
     form.method = 'POST';
     //set form th:action to add-product in basket controller
     form.action = '/nameZtoA';
     //var hiddenField = document.createElement('input');
    // hiddenField.type = 'hidden';
    // hidddenField.name = 'name';
    // hiddenField.value = AtoZ;
     document.body.appendChild(form);
    form.submit();
   }

  function showOptions() {
    const filterOptionsDropdown = document.getElementById('filter_options_dropdown');
  if (filterOptionsDropdown.style.display === 'none') {
    filterOptionsDropdown.style.display = 'block';

    document.getElementById('sort').classList.add('dropdown-open');
  } else {
    filterOptionsDropdown.style.display = 'none';
    document.getElementById('sort').classList.remove('dropdown-open');
  }
}
  
  
  function setLabel(text) {
    document.querySelector('.filter_option').innerHTML = `Sort by: ${text}`;
  }


function search () {
    let search = document.getElementById("search_bar");
  let cards = document.getElementsByClassName("prodmain");

   if (search != null) {

        for (var i = 0; i < cards.length ; i++){
            let name = cards[i].querySelector("h5").textContent;
            if (name.toLowerCase().startsWith(search.value.toLowerCase())) {
                cards[i].style.display = "";
            } else {
                cards[i].style.display = "none";
            }
          }
        }
     }



 


