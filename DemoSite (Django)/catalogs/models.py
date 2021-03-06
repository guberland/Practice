from django.db import models

# Create your models here.

class Catalog(models.Model):
    created_at = models.DateTimeField(auto_now_add = True)
    name = models.CharField(max_length = 255)
    description = models.TextField()
    
    
    def __str__(self):
        return self.name
    
    
class Procedure(models.Model):
    title = models.CharField(max_length = 255)
    description = models.TextField()
    content = models.TextField(blank=True,default="")
    order = models.IntegerField (default = 0)
    catalog = models.ForeignKey(Catalog)
    
    class Meta:
        ordering = ['order',]
    
    
    
    def __str__(self):
        return self.title