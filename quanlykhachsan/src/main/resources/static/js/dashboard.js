document.addEventListener('DOMContentLoaded', function(){
  const statusFilter = document.getElementById('statusFilter');
  if(statusFilter){
    statusFilter.addEventListener('change', function(){
      const val = statusFilter.value;
      // redirect tới controller với param status
      let url = '/dashboard';
      if(val && val !== 'Tất cả'){
         url += '?status=' + encodeURIComponent(val);
      }
      window.location.href = url;
    });
  }

  // toggle sidebar
  const btn = document.getElementById('menu-toggle');
  if(btn){
    btn.addEventListener('click', function(e){
      e.preventDefault();
      document.getElementById('sidebar-wrapper').classList.toggle('collapsed');
    });
  }
});
