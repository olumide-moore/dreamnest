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