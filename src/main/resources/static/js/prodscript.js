function addToCartItem(div){

  qty = document.getElementById('inputQuantity').value;
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

  //AJAX Call
$.ajax({
    url: 'prod.php',
    type: 'post',
    data: {
        searchString: searchString
    },
    success: function(data) {
        // handle the response data
    }
});


  //JavaScript Function (Capture Letters)
$('#search_bar').on('keyup', function(){
    let searchString = $(this).val();
    // make AJAX call
});


  // Display Results) from input from search bar
function displayResults(data){
    let html = '';
    for(let i = 0; i < data.length; i++){
        html += '<div class="product">';
        html += '<img src="'+data[i].image_path+'" alt="'+data[i].name+'">';
        html += '<h3>'+data[i].name+'</h3>';
        html += '<p>Price: $'+data[i].price+'</p>';
        html += '<p>Stock: '+data[i].stock+'</p>';
        html += '</div>';
    }
    // display the html
    $('#products').html(html);
}

window.onload = function () {
    let search = document.getElementById("search_bar");
    let cards = document.getElementsByClassName("card-prod h-100");

    if (search != null) {
        search.onkeyup = function () {
            let letters = search.value.toLowerCase().split(''); //splits the letters of the search input into an array 
            for (var i = 0; i < cards.length ; i++){
                let name = cards[i].querySelector("h5.fw-bolder").textContent.toLowerCase(); //converts the product name to lowercase for comparison 
                let nameLetters = name.split(''); //splits the letters of the product name into an array 
                let isMatch = true; //initializes a boolean that will change to false if the input and product name don't match 
                for (let j = 0; j < letters.length; j++){
                    if (nameLetters[j] != letters[j]){
                        isMatch = false; //if the input and product name don carry on with the code, set isMatch to false
                    }
                }
                if (isMatch){ //if the input matches the product name, set isMatch to true
                    cards[i].style.display = ""; //show the card 

                } else {
                    cards[i].style.display = "none"; //hide the card 
                }
            }
        }
    }
}







//window.onload = function () {
//    let search = document.getElementbyId("search_bar");
//   let cards = document.getElementsByClassName("card-prod h-100");

 //  if (search != null) {
 //   search.onkeyup = function2 () {
  //      for (var i = 0; i < cards.length ; i++){
   //         let name = cards[i].querySelector("h5.fw-bolder").textContent;
   //         if (name.toLowercase().includes(search.value.toLowercase())) {
    //            cards[i].style.display = "";
    //        } else {
   //             cards[i].style.display = "none";
    //        }
   //       }
   //     }
   //  }
  // }
   
   




    
  //sortBySelect.addEventListener('change', filterNameChange);

 // function searcgh
    //function search(input){
       // document.getElementById("myInput").addEventListener(onkeyup, () =>{
           // const form = document.createElement('form');
           // let input = document.getElementById("myInput").innerHTML.value;
    // form.method = 'POST';
     //set form th:action to add-product in basket controller
    // form.action = '/search';
  
    // var hiddenField = document.createElement('input');
    // hiddenField.type = 'hidden';
    // hiddenField.name = 'name';
    // hiddenField.value = input;
    // form.appendChild(hiddenField);
   // document.body.appendChild(form);
   // form.submit();
    //    });
  
  // }

   
  //<div class="filter_option" th:text="${text}" onclick="showOptions()">Sort by</div>
  
  


 


